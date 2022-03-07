package com.tabeldata.sipening.auth.usermanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

public class UmOtoritasDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Kelola{

        private Integer id;

        @NotBlank(message = "kode tidak boleh null atau kosong")
        @Size(max = 15, message = "kode tidak boleh lebih dari 15 huruf")
        private String kode;

        @NotBlank(message = "nama tidak boleh null atau kosong")
        @Size(max = 255, message = "nama tidak boleh lebih dari 255 huruf")
        private String nama;

        @NotBlank(message = "keterangan tidak boleh null atau kosong")
        @Size(max = 400, message = "keterangan tidak boleh lebih dari 400 huruf")
        private String keterangan;

        @NotNull(message = "aktif tidak boleh null")
        private Boolean aktif;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SetAktif{

        private List<DtRes> data;

        @NotNull(message = "aktif tidak boleh null")
        private Boolean aktif;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DtReq{

        private String kode;
        private String nama;
        private String keterangan;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DtRes{

        private Integer id;
        private String kode;
        private String nama;
        private String keterangan;
        private Boolean aktif;
        private Integer idPenggunaRekam;
        private Timestamp tanggalPenggunaRekam;
        private Integer idPenggunaUbah;
        private Timestamp tanggalPenggunaUbah;
    }
}
