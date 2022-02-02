package com.example.PersonCRUD.entity.apiResponse;

import lombok.Data;

@Data
public class ApiResponse {
    private String massage;
    private boolean success;
    private Object object;

    public ApiResponse(String massage, boolean success) {
        this.massage = massage;
        this.success = success;
    }

    public ApiResponse(boolean success, Object object) {
        this.success = success;
        this.object = object;
    }

    public ApiResponse(Object object) {
        this.object = object;
    }
}
