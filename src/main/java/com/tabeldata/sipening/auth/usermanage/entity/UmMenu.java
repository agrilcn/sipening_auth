package com.tabeldata.sipening.auth.usermanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmMenu {

    private Integer id;
    private Integer idInduk;
    private Integer idAplikasi;
    private String kode;
    private String nama;
    private String keterangan;
    private Integer noUrut;
    private String link;
    private String icon;
    private Boolean aktif;
    private Integer idPenggunaRekam;
    private Timestamp tanggalPenggunaRekam;
    private Integer idPenggunaUbah;
    private Timestamp tanggalPenggunaUbah;
}
