package com.tabeldata.sipening.auth.usermanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmAplikasi {

    private Integer id;
    private Integer noUrut;
    private String kode;
    private String nama;
    private String link;
    private String keterangan;
    private Boolean aktif;
    private Integer idPenggunaRekam;
    private Timestamp tanggalPenggunaRekam;
    private Integer idPenggunaUbah;
    private Timestamp tanggalPenggunaUbah;
}
