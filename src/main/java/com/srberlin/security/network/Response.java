package com.srberlin.security.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> {
    private T data;
    private String message;
    private int status;
    private boolean isError;

    public Response<T> success(T data, String message, int status) {
        return Response.<T>builder()
                .data(data)
                .message(message)
                .status(status)
                .isError(false)
                .build();
    }

    public Response<T> success(T data, String message) {
        return Response.<T>builder()
                .data(data)
                .message(message)
                .status(200)
                .isError(false)
                .build();
    }

    public Response<T> success(T data) {
        return Response.<T>builder()
                .data(data)
                .message("Success")
                .status(200)
                .isError(false)
                .build();
    }

    public Response<T> error(T data, String message, int status) {
        return Response.<T>builder()
                .data(data)
                .message(message)
                .status(status)
                .isError(true)
                .build();
    }

    public Response<T> error(T data) {
        return Response.<T>builder()
                .data(data)
                .message("Internal Server Error")
                .status(500)
                .isError(true)
                .build();
    }
}
