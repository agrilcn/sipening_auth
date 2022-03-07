package com.tabeldata.sipening.auth.usermanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UmOtoritasMenuDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Kelola{

        @NotNull(message = "idOtoritas tidak boleh null")
        private Integer idOtoritas;

        @NotNull(message = "idAplikasi tidak boleh null")
        private Integer idAplikasi;

        private List<Menu> menuList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Menu{

        private Integer id;
        private String kode;
        private String nama;
        private Boolean isSubMenu;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListMenu{

        private List<Menu> in;
        private List<Menu> notIn;
    }
}
