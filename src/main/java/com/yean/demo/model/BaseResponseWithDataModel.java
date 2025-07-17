package com.yean.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponseWithDataModel {
    private Object data;

    public BaseResponseWithDataModel(String message, int status, Object data) {
        super();
        this.data = data;
    }
}
