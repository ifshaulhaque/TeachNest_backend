package com.example.TeachNest.utils;

import com.example.TeachNest.models.ResponseModel;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static ResponseEntity<ResponseModel> getResponse(ResponseModel responseModel) {
        return new ResponseEntity<>(responseModel, responseModel.getHttpStatus());
    }
}
