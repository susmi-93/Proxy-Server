package com.httpclient.proxy.service;

import com.httpclient.proxy.model.ProxyModel;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ProxyService {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    public String getUrl(ProxyModel model) throws IOException {
        String result = "";
        HttpGet request = new HttpGet(model.getUrl());
        for(Map.Entry header: model.getHeaders().entrySet()){
            request.setHeader(header.getKey().toString(), header.getValue().toString());
        }
        CloseableHttpResponse response = httpClient.execute(request);
        try{
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
        }finally {
            response.close();
        }
        return result;
    }

    public String postUrl(ProxyModel model) throws IOException {
        String result = "";
        HttpPost post = new HttpPost(model.getUrl());
        List<NameValuePair> urlParameters = new ArrayList<>();
        for(Map.Entry header: model.getHeaders().entrySet()){
            post.setHeader(header.getKey().toString(), header.getValue().toString());
        }
        for(Map.Entry formData: model.getFormData().entrySet()){
            urlParameters.add(new BasicNameValuePair(formData.getKey().toString(), formData.getValue().toString()));
        }
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)){
            result = EntityUtils.toString(response.getEntity());
        }
        return result;
    }

    public Map<String,String> getFormData(String body){
        Map<String, String> formData = new HashMap<String, String>();
        List<String> params = Arrays.asList(body.split("&"));
        for(String param : params){
            String[] data = param.split("=");
            formData.put(data[0], data[1]);
        }
        return formData;
    }
}
