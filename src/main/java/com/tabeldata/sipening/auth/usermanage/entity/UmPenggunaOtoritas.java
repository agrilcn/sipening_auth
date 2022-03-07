package com.tabeldata.sipening.auth.usermanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmPenggunaOtoritas {

    private Integer id;
    private Integer idPengguna;
    private Integer idOtoritas;
    private Integer idPenggunaRekam;
    private Timestamp tanggalPenggunaRekam;
}
