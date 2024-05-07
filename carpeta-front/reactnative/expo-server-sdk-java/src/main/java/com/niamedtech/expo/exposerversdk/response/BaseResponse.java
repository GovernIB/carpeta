package com.niamedtech.expo.exposerversdk.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Base class for responses provied by Expo Push Notification Service. */

public abstract class BaseResponse<T> {

    protected static class GenericData {

        /** Store unmapped data in case actual response is varying from specification. */
        private Map<String, JsonNode> any;

        @JsonAnyGetter
        public Map<String, JsonNode> getAny() {
            return any;
        }

        @JsonAnySetter
        public void addAny(String key, JsonNode value) {
            if (any == null) {
                any = new HashMap<>();
            }
            any.put(key, value);
        }
    }

 
    public static class Error extends GenericData {
        private String code;
        private String message;
        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }
        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        
        
        
    }

    public abstract T getData();

    private List<Error> errors;

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

}
