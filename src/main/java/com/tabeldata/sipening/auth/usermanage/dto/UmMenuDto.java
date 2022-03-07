package com.tabeldata.sipening.auth.usermanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

public class UmMenuDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Kelola{

        private Integer id;

        @NotNull(message = "idAplikasi tidak boleh null")
        private Integer idAplikasi;

        private Integer idInduk;

        private Integer noUrut;

        @NotBlank(message = "kode tidak boleh null atau kosong")
        @Size(max = 25, message = "kode tidak boleh lebih dari 25 huruf")
        private String kode;

        @NotBlank(message = "nama tidak boleh null atau kosong")
        @Size(max = 255, message = "nama tidak boleh lebih dari 255 huruf")
        private String nama;

        @NotBlank(message = "keterangan tidak boleh null atau kosong")
        @Size(max = 400, message = "keterangan tidak boleh lebih dari 400 huruf")
        private String keterangan;

        @NotBlank(message = "link tidak boleh null atau kosong")
        @Size(max = 255, message = "link tidak boleh lebih dari 255 huruf")
        private String link;

        @NotBlank(message = "icon tidak boleh null atau kosong")
        @Size(max = 255, message = "icon tidak boleh lebih dari 255 huruf")
        private String icon;

        @NotNull(message = "aktif tidak boleh null")
        private Boolean aktif;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SetAktif{

        private List<UmOtoritasDto.DtRes> data;

        @NotNull(message = "aktif tidak boleh null")
        private Boolean aktif;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DtReq{

        private String nama;
        private String link;
        private String keterangan;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DtRes{

        private Integer id;
        private Integer idInduk;
        private Integer idAplikasi;
        private String kode;
        private String nama;
        private String keterangan;
        private Integer noUrut;
        private String tipeMenu;
        private String link;
        private String icon;
        private Boolean aktif;
        private Integer idPenggunaRekam;
        private Timestamp tanggalPenggunaRekam;
        private Integer idPenggunaUbah;
        private Timestamp tanggalPenggunaUbah;
    }
}
