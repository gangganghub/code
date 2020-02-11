package com.origin.client.hystrix;


import org.springframework.stereotype.Component;

import com.origin.client.AuthServiceClient;
import com.origin.entity.JWT;

@Component
public class AuthServiceHystrix implements AuthServiceClient {
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        return null;
    }
}
