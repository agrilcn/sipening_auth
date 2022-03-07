package com.tabeldata.sipening.auth.usermanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UmPenggunaOtoritasDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Kelola{

        @NotNull(message = "idPengguna tidak boleh null")
        private Integer idPengguna;

        private List<Otor> otoritasList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Otor{

        private Integer id;
        private String kode;
        private String nama;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListOtor{

        private List<Otor> in;
        private List<Otor> notIn;
    }
}
