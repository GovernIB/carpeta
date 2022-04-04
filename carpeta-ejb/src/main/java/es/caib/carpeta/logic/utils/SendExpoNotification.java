package es.caib.carpeta.logic.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;

import io.github.jav.exposerversdk.ExpoPushMessage;
import io.github.jav.exposerversdk.ExpoPushMessageTicketPair;
import io.github.jav.exposerversdk.ExpoPushReceipt;
import io.github.jav.exposerversdk.ExpoPushTicket;
import io.github.jav.exposerversdk.PushClient;
import io.github.jav.exposerversdk.enums.Status;

/**
 * 
 * @author anadal
 *
 */
public class SendExpoNotification {

    protected static Logger log = Logger.getLogger(SendExpoNotification.class);

    /**
     * 
     * @param mobileid
     * @param title
     * @param message
     * @param code
     * @return
     * @throws I18NException
     */
    public static ResultSendNotification sendMessageToMobile(String mobileid, String title, String message, Map<String, Object> data)
            throws I18NException {

        StringBuilder result = new StringBuilder();
        Map<String, ResultSendNotification> resultatEnviament = new HashMap<String, ResultSendNotification>();
        try {

            if (!PushClient.isExponentPushToken(mobileid)) {
                throw new I18NException("genapp.comodi", "Token:" + mobileid + " is not a valid token.");
            }

            ExpoPushMessage expoPushMessage = new ExpoPushMessage();
            expoPushMessage.addTo(mobileid);
            expoPushMessage.setTitle(title);
            expoPushMessage.setBody(message);
            expoPushMessage.setData(data);

            List<ExpoPushMessage> expoPushMessages = new ArrayList<>();
            expoPushMessages.add(expoPushMessage);

            PushClient client = new PushClient();
            List<List<ExpoPushMessage>> chunks = client.chunkPushNotifications(expoPushMessages);

            List<CompletableFuture<List<ExpoPushTicket>>> messageRepliesFutures = new ArrayList<>();

            for (List<ExpoPushMessage> chunk : chunks) {
                messageRepliesFutures.add(client.sendPushNotificationsAsync(chunk));
            }

            // Wait for each completable future to finish

            List<ExpoPushTicket> allTickets = new ArrayList<>();

            boolean hanacabattotes = true;
            do {
                hanacabattotes = true;
                allTickets.clear();
                Thread.sleep(250);
                for (CompletableFuture<List<ExpoPushTicket>> messageReplyFuture : messageRepliesFutures) {
                    if (messageReplyFuture.isDone()) {
                        for (ExpoPushTicket ticket : messageReplyFuture.get()) {
                            allTickets.add(ticket);
                        }
                    } else {
                        hanacabattotes = false;
                        break;
                    }
                }

                log.info("Han acabat totes? " + hanacabattotes);

            } while (!hanacabattotes);

            List<ExpoPushMessageTicketPair<ExpoPushMessage>> zippedMessagesTickets = client
                    .zipMessagesTickets(expoPushMessages, allTickets);

            List<ExpoPushMessageTicketPair<ExpoPushMessage>> okTicketMessages = client
                    .filterAllSuccessfulMessages(zippedMessagesTickets);

            String okTicketMessagesString = okTicketMessages.stream()
                    .map(p -> "Title: " + p.message.getTitle() + ", Id:" + p.ticket.getId())
                    .collect(Collectors.joining(","));
            result.append("Recieved OK ticket for " + okTicketMessages.size() + " messages: " + okTicketMessagesString
                    + "\n");
            
            for(ExpoPushMessageTicketPair<ExpoPushMessage> ok : okTicketMessages) {
                
                String id = ok.ticket.getId();
                ResultSendNotification rsn = new ResultSendNotification(id, ok.message.getTo());
                
                resultatEnviament.put(id, rsn);
            }
            

            List<ExpoPushMessageTicketPair<ExpoPushMessage>> errorTicketMessages = client
                    .filterAllMessagesWithError(zippedMessagesTickets);
            String errorTicketMessagesString = errorTicketMessages.stream()
                    .map(p -> "Title: " + p.message.getTitle() + ", Error: " + p.ticket.getDetails().getError())
                    .collect(Collectors.joining(","));
            result.append("Recieved ERROR ticket for " + errorTicketMessages.size() + " messages: "
                    + errorTicketMessagesString);
            
            for(ExpoPushMessageTicketPair<ExpoPushMessage> errorResult : errorTicketMessages) {
                
                String id = errorResult.ticket.getId();
                ResultSendNotification rsn;
                rsn = new ResultSendNotification(id, errorResult.message.getTo(), errorResult.ticket.getDetails().getError().toString());
                
                resultatEnviament.put(id, rsn);
            }
            
            

            // Countdown 15s
            int waitTotalSec = 15;
            long waitMsIteration = 3000;

            int wait = (int) ((waitTotalSec * 1000) / waitMsIteration);
            for (int i = wait; i >= 0; i--) {
                result.append("Waiting for " + waitMsIteration / 1000 + " seconds. " + i + "s\n");
                Thread.sleep(waitMsIteration);

                result.append("Fetching reciepts...\n");

                List<String> ticketIds = (client.getTicketIdsFromPairs(okTicketMessages));
                CompletableFuture<List<ExpoPushReceipt>> receiptFutures = client
                        .getPushNotificationReceiptsAsync(ticketIds);

                List<ExpoPushReceipt> receipts = new ArrayList<>();
                try {
                    receipts = receiptFutures.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                result.append("Recieved " + receipts.size() + " receipts:\n");

                if (receipts.size() != 0) {

                    for (ExpoPushReceipt reciept : receipts) {
                        
                        Status s = reciept.getStatus();
                        
                        String id = reciept.getId();
                        
                        ResultSendNotification rsn = resultatEnviament.get(id);
                        
                        if (s == Status.OK) {
                            rsn.setEstatRebut(true);
                        } else {
                            rsn.setEstatRebut(false);
                        }
                        
                        result.append(
                                "Receipt for id: " + id + " had status: " + s + "\n");
                    }
                    break;
                }
            }
            
            if (resultatEnviament.size() == 0) {
                return null;
            } else {
                
                ArrayList<ResultSendNotification> list = new ArrayList<ResultSendNotification>(resultatEnviament.values());
                
                return list.get(0);
            }

        } catch (I18NException i18n) {

            throw i18n;

        } catch (Throwable e) {

            // TODO: handle exception
            String msg = "Error no controlat enviant missatge a mòbil: " + e.getMessage();
            log.error(msg, e);
            throw new I18NException("genapp.comodi", msg);
        } finally {

            log.warn("--------- LOG ------");
            log.warn(result.toString());
            log.warn("--------------------");
        }

    }

    public static class ResultSendNotification {

        protected final List<String> mobileId;
        protected final String messageId;
        protected final boolean estatEnviat;
        // Re
        protected boolean estatRebut = false;
        
        protected String error = null;
        

        public ResultSendNotification(String messageId, List<String> mobileId) {
            super();
            this.messageId = messageId;
            this.mobileId = mobileId;
            this.estatEnviat = true;
        }
        
        public ResultSendNotification(String messageId, List<String> mobileId, String error) {
            super();
            this.messageId = messageId;
            this.mobileId = mobileId;
            this.estatEnviat = false;
            this.error = error;
        }

        public String getMessageId() {
            return messageId;
        }

        public boolean getEstatEnviat() {
            return estatEnviat;
        }

        public boolean getEstatRebut() {
            return estatRebut;
        }

        public void setEstatRebut(boolean estatRebut) {
            this.estatRebut = estatRebut;
        }

        public List<String> getMobileId() {
            return mobileId;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
        
        
        @Override
        public String toString() {
            return "MobileID: " + this.mobileId + "\n"
                    + "NotificationID: " + this.messageId + "\n"
                    + "Enviament: " + this.estatEnviat + "\n"
                    + "Recepció: " + this.estatRebut
                    + ((this.error == null)? "" : ("\nError: " + this.error));
        }

    }

}
