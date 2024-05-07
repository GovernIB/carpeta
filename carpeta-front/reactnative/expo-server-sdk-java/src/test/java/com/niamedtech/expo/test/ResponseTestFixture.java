package com.niamedtech.expo.test;

public final class ResponseTestFixture {

  private ResponseTestFixture() {
    throw new UnsupportedOperationException();
  }

  public static final String RECEIPT_ID_1 = "caa6fe6e-85d8-456c-bf57-e8cdc3fcb137";

  public static final String PUSH_SEND_OK_SINGLE_RESPONSE =
      "            {\r\n"
      + "                \"data\": [\r\n"
      + "                    {\r\n"
      + "                        \"status\": \"ok\",\r\n"
      + "                         \"id\": \"caa6fe6e-85d8-456c-bf57-e8cdc3fcb137\"\r\n"
      + "                    }\r\n"
      + "                ]\r\n"
      + "            }";

  public static final String GET_RECEIPT_OK_SINGLE_RESPONSE =
      "            {\r\n"
      + "                \"data\": {\r\n"
      + "                    \"caa6fe6e-85d8-456c-bf57-e8cdc3fcb137\": {\r\n"
      + "                        \"status\": \"ok\"\r\n"
      + "                    }\r\n"
      + "                }\r\n"
      + "            }";

  public static final String RECEIPT_ID_2 = "0d44e896-1a04-4409-a56e-a7383641cdb7";

  public static final String RECEIPT_ID_3 = "0d44e896-1a04-4409-a56e-a7383641cdb7";

  public static final String PUSH_SEND_OK_MULTIPLE_RESPONSE =
      "            {\r\n"
      + "                \"data\":[\r\n"
      + "                    {\r\n"
      + "                        \"status\":\"ok\",\r\n"
      + "                        \"id\":\"0d44e896-1a04-4409-a56e-a7383641cdb7\"\r\n"
      + "                    },\r\n"
      + "                    {\r\n"
      + "                        \"status\":\"ok\",\r\n"
      + "                        \"id\":\"d6d93f26-a037-44a4-844d-c5c458844e9b\"\r\n"
      + "                    }\r\n"
      + "                ]\r\n"
      + "            }";

  public static final String GET_RECEIPT_OK_MULTIPLE_RESPONSE =
      "            {\r\n"
      + "                \"data\":{\r\n"
      + "                    \"0d44e896-1a04-4409-a56e-a7383641cdb7\":{\r\n"
      + "                        \"status\":\"ok\"\r\n"
      + "                    },\r\n"
      + "                    \"d6d93f26-a037-44a4-844d-c5c458844e9b\":{\r\n"
      + "                        \"status\":\"ok\"\r\n"
      + "                    }\r\n"
      + "                }\r\n"
      + "            }";

  public static final String PUSH_SEND_VALIDATION_ERROR_RESPONSE =
      "            {\r\n"
      + "                \"errors\":[\r\n"
      + "                    {\r\n"
      + "                        \"code\":\"VALIDATION_ERROR\",\r\n"
      + "                        \"message\":\"[0].data must be of type object.\",\r\n"
      + "                        \"isTransient\":false,\r\n"
      + "                        \"requestId\":\"96995d9b-1530-48ad-8501-7410acd9d2c6\"\r\n"
      + "                    }\r\n"
      + "                ]\r\n"
      + "            }";
}
