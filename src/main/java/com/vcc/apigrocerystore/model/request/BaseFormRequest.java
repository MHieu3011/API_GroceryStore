package com.vcc.apigrocerystore.model.request;

public class BaseFormRequest {

    protected String requestUri;

    public BaseFormRequest() {
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
    }

    public BaseFormRequest(String requestUri) {
        this.requestUri = requestUri;
    }
}
