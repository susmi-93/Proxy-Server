package com.httpclient.proxy.model;

import java.util.Map;

public class ProxyModel {
    String requestType;
    String url;
    Map<String,String> headers;
    Map<String,String> formData;

    public ProxyModel(String requestType, String url, Map<String, String> headers, Map<String, String> formData) {
        this.requestType = requestType;
        this.url = url;
        this.headers = headers;
        this.formData = formData;
    }

    public String getRequestType() {
        return requestType.toUpperCase();
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getFormData() {
        return formData;
    }

    public void setFormData(Map<String, String> formData) {
        this.formData = formData;
    }
}
