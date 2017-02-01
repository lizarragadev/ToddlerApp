package com.miramicodigo.toddler.data.entity.response;

/**
 * Created by gusn8 on 30-01-17.
 */

public class BaseResponse {
    private static final int SUCCESS = 200;
    private String msg;
    private int status;

    public boolean isSuccess() {
        if(this.status == SUCCESS) {
            return true;
        }
        return false;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
