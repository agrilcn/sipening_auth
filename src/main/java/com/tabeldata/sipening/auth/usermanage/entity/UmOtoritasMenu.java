package com.tabeldata.sipening.auth.usermanage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmOtoritasMenu {

    private Integer id;
    private Integer idOtoritas;
    private Integer idMenu;
    private Integer idPenggunaRekam;
    private Timestamp tanggalPenggunaRekam;
}
