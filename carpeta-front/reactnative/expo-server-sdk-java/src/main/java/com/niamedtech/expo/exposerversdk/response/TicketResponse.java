package com.niamedtech.expo.exposerversdk.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;


/** Response inlcuding tickets for push notifications. */

public final class TicketResponse extends BaseResponse<List<TicketResponse.Ticket>> {

    public static class Ticket extends BaseResponse.GenericData {

        public enum Error {
            @JsonProperty("DeviceNotRegistered")
            DEVICE_NOT_REGISTERED, @JsonProperty("InvalidCredentials")
            INVALID_CREDENTIALS;
        }

 
        public static class Details {
            private Error error;
            private Integer sentAt;
            private JsonNode additionalProperties;

            public Error getError() {
                return error;
            }

            public void setError(Error error) {
                this.error = error;
            }

            public Integer getSentAt() {
                return sentAt;
            }

            public void setSentAt(Integer sentAt) {
                this.sentAt = sentAt;
            }

            public JsonNode getAdditionalProperties() {
                return additionalProperties;
            }

            public void setAdditionalProperties(JsonNode additionalProperties) {
                this.additionalProperties = additionalProperties;
            }

        }

        private String id;
        private Status status;
        private String message;
        private Details details;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Details getDetails() {
            return details;
        }

        public void setDetails(Details details) {
            this.details = details;
        }

    }

    private List<TicketResponse.Ticket> data;

    public List<TicketResponse.Ticket> getData() {
        return data;
    }

    public void setData(List<TicketResponse.Ticket> data) {
        this.data = data;
    }

}
