package com.jn.api_gastos.config.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ApiResponseErrors {
    private Date time = new Date();
    private String message;
    private String url;

    public ApiResponseErrors(String message, String url) {
        this.message = message;
        this.url = url.replace("uri=", "");
    }
}
