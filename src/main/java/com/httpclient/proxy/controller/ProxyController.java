package com.httpclient.proxy.controller;

import com.httpclient.proxy.model.ProxyModel;
import com.httpclient.proxy.service.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProxyController {
    @Autowired
    private ProxyService service;

    @GetMapping("/proxy/**")
    public String getUrl(HttpServletRequest request) throws IOException {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("User-Agent", request.getHeader("User-Agent"));
        String requestUrl = request.getRequestURI().replaceAll("/proxy/","");
        ProxyModel model = new ProxyModel("GET",requestUrl, headers, null);
        String result = service.getUrl(model);
        return result;
    }

    @PostMapping("/proxy/**")
    public String postUrl(HttpServletRequest request, HttpEntity<String> httpEntity) throws IOException {
        Map<String,String> headers = new HashMap<String,String>();
        headers.put("User-Agent", request.getHeader("User-Agent"));
        String requestUrl = request.getRequestURI().replaceAll("/proxy/","");
        Map<String,String> formData = service.getFormData(httpEntity.getBody());
        ProxyModel model = new ProxyModel("POST",requestUrl, headers, formData);
        String result = service.postUrl(model);
        return result;
    }
}
