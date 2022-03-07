package com.tabeldata.sipening.auth.usermanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmOtoritasAplikasi {

    private Integer id;
    private Integer idOtoritas;
    private Integer idAplikasi;
    private Integer idPenggunaRekam;
    private Timestamp tanggalPenggunaRekam;
}
