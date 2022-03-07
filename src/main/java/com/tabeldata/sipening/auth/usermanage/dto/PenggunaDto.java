package com.tabeldata.sipening.auth.usermanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class PenggunaDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info{

        private Integer id;
        private String nrk;
        private String email;
        private String noHp;
        private String nip;
        private String nama;
        private String jabatan;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccessMenu{

        private Boolean isAllowed;
        private String namaMenu;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Aplikasi{

        private Integer id;
        private String kode;
        private String nama;
        private String link;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AplikasiWithMenu{

        private Integer id;
        private String kode;
        private String nama;
        private String link;
        private List<Menu> menu;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Menu{

        private Integer id;
        private Integer idInduk;
        private String nama;
        private String keterangan;
        private String link;
        private String icon;
        private List<SubMenu> subMenu;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SubMenu{

        private Integer id;
        private Integer idInduk;
        private String nama;
        private String keterangan;
        private String link;
        private String icon;
    }
}
