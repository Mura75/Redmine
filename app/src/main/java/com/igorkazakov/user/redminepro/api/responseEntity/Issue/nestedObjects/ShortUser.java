package com.igorkazakov.user.redminepro.api.responseEntity.Issue.nestedObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 28.07.17.
 */

public class ShortUser {

    @SerializedName("id")
    @Expose
    private Long shortUserId;
    @SerializedName("name")
    @Expose
    private String shortUserName;

    public Long getShortUserId() {
        return shortUserId;
    }

    public void setShortUserId(Long id) {
        this.shortUserId = id;
    }

    public String getShortUserName() {
        return shortUserName;
    }

    public void setShortUserName(String name) {
        this.shortUserName = name;
    }
}
