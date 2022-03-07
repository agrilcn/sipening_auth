package com.tabeldata.sipening.auth.usermanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ErrorDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NotFound{

        private String message;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NotAllowed{

        private String message;
    }
}
