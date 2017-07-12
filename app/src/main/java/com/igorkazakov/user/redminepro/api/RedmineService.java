package com.igorkazakov.user.redminepro.api;

import com.igorkazakov.user.redminepro.api.response.LoginResponse;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by user on 11.07.17.
 */

public interface RedmineService {

    @GET("/users/current.json")
    Observable<LoginResponse> login(@Header("Authorization") String authorization,
                                    @Header("Content-Type") String contentType);

}