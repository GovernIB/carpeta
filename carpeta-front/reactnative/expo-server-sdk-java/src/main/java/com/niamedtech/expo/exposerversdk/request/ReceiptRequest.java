package com.niamedtech.expo.exposerversdk.request;

import java.util.List;




public final class ReceiptRequest {

    private final List<String> ids;

    public ReceiptRequest( List<String> ids) {
        super();
        this.ids = ids;
    }

    public List<String> getIds() {
        return ids;
    }

    
}
