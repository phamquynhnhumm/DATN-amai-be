package com.example.amai.core.security.service;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;


@Service
public class OtpService {

    //cache based on username and OPT MAX 5
    private static final Integer EXPIRE_MIN = 5;

    private final LoadingCache<String, String> otpCache;

    public OtpService() {
        super();
        otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES).build(new CacheLoader<String, String>() {
            public String load(String userName) {
                return null;
            }
        });
    }

    //This method is used to push the opt number against Key. Rewrite the OTP if it exists
    //Using user id  as key
    public String generateOTP(String userName) {
        String otp = RandomStringUtils.randomAlphabetic(6);
        otpCache.put(userName, otp);
        return otp;
    }

    //This method is used to return the OPT number against Key->Key values is username
    public String getOtp(String userName) {
        try {
            return otpCache.get(userName);
        } catch (Exception e) {
            return null;
        }
    }

    //This method is used to clear the OTP catched already
    public void clearOTP(String userName) {
        otpCache.invalidate(userName);
    }
}
