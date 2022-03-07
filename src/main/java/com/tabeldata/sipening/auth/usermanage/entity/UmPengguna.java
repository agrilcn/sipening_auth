package com.tabeldata.sipening.auth.usermanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmPengguna {

    private Integer id;
    private String nrk;
    private String sandi;
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
