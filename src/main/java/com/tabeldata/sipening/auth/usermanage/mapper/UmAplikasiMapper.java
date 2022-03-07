package com.tabeldata.sipening.auth.usermanage.mapper;

import com.tabeldata.sipening.auth.usermanage.dto.UmAplikasiDto;
import com.tabeldata.sipening.auth.usermanage.entity.UmAplikasi;
import org.springframework.stereotype.Component;

@Component
public class UmAplikasiMapper {

    public static UmAplikasi kelola(UmAplikasiDto.Kelola value) {
        UmAplikasi entity = new UmAplikasi();
        entity.setId(value.getId());
        entity.setNoUrut(value.getNoUrut());
        entity.setKode(value.getKode());
        entity.setNama(value.getNama());
        entity.setLink(value.getLink());
        entity.setKeterangan(value.getKeterangan());
        entity.setAktif(value.getAktif());
        return entity;
    }

    public static Integer getIdFromDtRes(UmAplikasiDto.DtRes value) {
        return value.getId();
    }
}
