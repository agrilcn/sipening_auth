package com.tabeldata.sipening.auth.usermanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UmOtoritasAplikasiDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Kelola{

        @NotNull(message = "idOtoritas tidak boleh null")
        private Integer idOtoritas;

        private List<App> appList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class App{

        private Integer id;
        private String kode;
        private String nama;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListApp{

        private List<App> in;
        private List<App> notIn;
    }
}
