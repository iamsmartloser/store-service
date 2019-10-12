package com.liyan.store.service;

import com.liyan.store.utils.Sign;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class RongHttpClient {
    private static final String APPKEY = "App-Key";
    private static final String NONCE = "Nonce";
    private static final String TIMESTAMP = "Timestamp";
    private static final String SIGNATURE = "Signature";



    public String client(String url, HttpMethod method, MultiValueMap<String, String> params){
        //String nonce = String.valueOf(Math.random() * 1000000);
        String nonce="";
        Random random=new Random();
        for(int i=0;i<5;i++){
            nonce+=random.nextInt(10);
        }
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        StringBuilder toSign = new StringBuilder("mONR4IoaFmDWr").append(nonce).append(timestamp);
        String sign = Sign.hexSHA1(toSign.toString());

        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add(APPKEY,"pwe86ga5e6zu6");
        headers.add(NONCE,nonce);
        headers.add(TIMESTAMP,timestamp);
        headers.add(SIGNATURE,sign);
        System.out.println("appkey:"+"pwe86ga5e6zu6");
        System.out.println("nonce:"+nonce);
        System.out.println("timestamp:"+timestamp);
        System.out.println("sign:"+sign);
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        //  执行HTTP请求
        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
        System.out.println(response.getBody());
        return response.getBody();
    }
}
