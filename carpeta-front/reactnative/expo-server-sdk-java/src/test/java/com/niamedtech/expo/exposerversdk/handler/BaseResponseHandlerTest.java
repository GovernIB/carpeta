package com.niamedtech.expo.exposerversdk.handler;




final class BaseResponseHandlerTest  {
/*
    @Mock
    private ClassicHttpResponse httpResponse;

    @Mock
    private HttpEntity httpEntity;

    @Test
    void testReceiveMultipleOkReceipts() throws Exception {
        when(httpResponse.getCode()).thenReturn(200);
        when(httpEntity.getContent()).thenReturn(new ByteArrayInputStream(GET_RECEIPT_OK_MULTIPLE_RESPONSE.getBytes()));
        when(httpResponse.getEntity()).thenReturn(httpEntity);

        final BaseResponseHandler<Map<String, ReceiptResponse.Receipt>> testee = new BaseResponseHandler<>(
                ReceiptResponse.class);
        final Map<String, Receipt> map = testee.handleResponse(httpResponse);

        assertThat(map.size(), is(2));

        final Receipt receipt2 = map.get(RECEIPT_ID_2);
        assertThat(receipt2, notNullValue());
        assertThat(receipt2.getStatus(), is(Status.OK));

        final Receipt receipt3 = map.get(RECEIPT_ID_3);
        assertThat(receipt3, notNullValue());
        assertThat(receipt3.getStatus(), is(Status.OK));
    }

    @Test
    void testInvalidFormat() throws Exception {

        when(httpResponse.getCode()).thenReturn(400);
        when(httpEntity.getContent())
                .thenReturn(new ByteArrayInputStream(PUSH_SEND_VALIDATION_ERROR_RESPONSE.getBytes()));
        when(httpResponse.getEntity()).thenReturn(httpEntity);

        final BaseResponseHandler<List<TicketResponse.Ticket>> testee = new BaseResponseHandler<>(TicketResponse.class);
        final HttpException exception = assertThrows(HttpException.class, () -> testee.handleResponse(httpResponse));
        assertThat(exception.getCause(), instanceOf(ErrorResponseException.class));
    }
    */
}
