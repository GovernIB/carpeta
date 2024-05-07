package com.niamedtech.expo.exposerversdk.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;


/** Reponse including receipts for tickets. */

public final class ReceiptResponse extends BaseResponse<Map<String, ReceiptResponse.Receipt>> {


    public static class Receipt extends BaseResponse.GenericData {


        public static class Details {

            public enum Error {
                @JsonProperty("DeviceNotRegistered")
                DEVICE_NOT_REGISTERED,

                @JsonProperty("MessageTooBig")
                MESSAGE_TOO_BIG,

                @JsonProperty("MessageRateExceeded")
                MESSAGE_RATE_EXCEEDED,

                @JsonProperty("InvalidCredentials")
                INVALID_CREDENTIALS,

                @JsonProperty("InvalidProviderToken")
                INVALID_PROVIDERTOKEN;
            }

            private Error error;
            private Integer sentAt;
            private String errorCodeEnum;
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

            public String getErrorCodeEnum() {
                return errorCodeEnum;
            }

            public void setErrorCodeEnum(String errorCodeEnum) {
                this.errorCodeEnum = errorCodeEnum;
            }

            public JsonNode getAdditionalProperties() {
                return additionalProperties;
            }

            public void setAdditionalProperties(JsonNode additionalProperties) {
                this.additionalProperties = additionalProperties;
            }

        }

        private Status status;
        private String message;
        private Details details;

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

    private Map<String, ReceiptResponse.Receipt> data;

    public Map<String, ReceiptResponse.Receipt> getData() {
        return data;
    }

    public void setData(Map<String, ReceiptResponse.Receipt> data) {
        this.data = data;
    }
    
    
    
    
}
