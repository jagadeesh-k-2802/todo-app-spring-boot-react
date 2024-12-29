package com.jagadeesh.todo_app.payload.response;

public class MessageResponse<T> {
    private Boolean success;
    private T data;

    public MessageResponse(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
