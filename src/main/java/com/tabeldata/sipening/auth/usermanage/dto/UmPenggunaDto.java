package com.tabeldata.sipening.auth.usermanage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.List;

public class UmPenggunaDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Kelola{

        private Integer id;

        @NotBlank(message = "nrk tidak boleh null atau kosong")
        @Size(max = 10, message = "nrk tidak boleh lebih dari 10 huruf")
        private String nrk;

        private String sandi;

        @Size(max = 100, message = "email tidak boleh lebih dari 100 huruf")
        private String email;

        @Size(max = 20, message = "noHp tidak boleh lebih dari 20 huruf")
        private String noHp;

        @Size(max = 18, message = "nip tidak boleh lebih dari 18 huruf")
        private String nip;

        @Size(max = 50, message = "nrk tidak boleh lebih dari 50 huruf")
        private String nama;

        @Size(max = 50, message = "nrk tidak boleh lebih dari 50 huruf")
        private String jabatan;

        @NotNull(message = "aktif tidak boleh null")
        private Boolean aktif;

        @NotNull(message = "kunci tidak boleh null")
        private Boolean kunci;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChangePassword{

        @NotNull(message = "id tidak boleh null")
        private Integer id;

        @NotBlank(message = "sandi tidak boleh null")
        private String sandi;

        @NotBlank(message = "konfirmasiSandi tidak boleh null")
        private String konfirmasiSandi;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SetLock{

        private List<UmOtoritasDto.DtRes> data;

        @NotNull(message = "kunci tidak boleh null")
        private Boolean kunci;
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

        private String nrk;
        private String email;
        private String nip;
        private String nama;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DtRes{

        private Integer id;
        private String nrk;
        private String email;
        private String noHp;
        private String nip;
        private String nama;
        private String jabatan;
        private Boolean aktif;
        private Boolean kunci;
        private Integer idPenggunaRekam;
        private Timestamp tanggalPenggunaRekam;
        private Integer idPenggunaUbah;
        private Timestamp tanggalPenggunaUbah;
    }
}
