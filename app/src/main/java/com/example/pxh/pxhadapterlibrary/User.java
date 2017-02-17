package com.example.pxh.pxhadapterlibrary;

/**
 * Created by xiaohong.peng on 2017/2/17.
 */

public class User {
    private String message;
    private String type;

    public User(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
