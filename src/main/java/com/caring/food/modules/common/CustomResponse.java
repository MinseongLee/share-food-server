package com.caring.food.modules.common;

import lombok.*;

@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class CustomResponse {
    private String statusCode;
    private String resultMsg;
    private Object dto;

    public static CustomResponse defaultCustomResponse() {
        return CustomResponse.builder()
                .statusCode("200")
                .resultMsg("success")
                .build();
    }
    public static CustomResponse customResponse(String statusCode, String resultMsg) {
        return CustomResponse.builder()
                .statusCode(statusCode)
                .resultMsg(resultMsg)
                .build();
    }
}
