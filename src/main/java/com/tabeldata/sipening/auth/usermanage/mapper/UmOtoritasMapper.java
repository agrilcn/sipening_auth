package com.tabeldata.sipening.auth.usermanage.mapper;

import com.tabeldata.sipening.auth.usermanage.dto.UmOtoritasDto;
import com.tabeldata.sipening.auth.usermanage.entity.UmOtoritas;
import org.springframework.stereotype.Component;

@Component
public class UmOtoritasMapper {

    public static UmOtoritas kelola(UmOtoritasDto.Kelola value) {
        UmOtoritas entity = new UmOtoritas();
        entity.setId(value.getId());
        entity.setKode(value.getKode());
        entity.setNama(value.getNama());
        entity.setKeterangan(value.getKeterangan());
        entity.setAktif(value.getAktif());
        return entity;
    }

    public static Integer getIdFromDtRes(UmOtoritasDto.DtRes value) {
        return value.getId();
    }
}
