package com.example.japanese_app_android.retrofit;

import java.io.IOException;

import lombok.Value;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkdWNAZ21haWwuY29tIiwiaWF0IjoxNjk4NzQ2OTI3LCJleHAiOjE2OTg3NTI5MjcsInNjb3BlIjpbIlNUVURFTlQiXX0.jgoQw88OO-1AQFM4a3jkI-Ze4rPLv6aH-LT259VBavgwAXuvkDBJlkP3sm76C7642wWHTUoJlfDsfhQFKfAfBQ";

    @Override
    public Response intercept(Chain chain) throws IOException {

        //rewrite the request to add bearer token
        Request newRequest=chain.request().newBuilder()
                .header("Authorization","Bearer "+ token)
                .build();

        return chain.proceed(newRequest);
    }
}
