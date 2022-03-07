package com.tabeldata.sipening.auth.usermanage.mapper;

import com.tabeldata.sipening.auth.usermanage.dto.UmMenuDto;
import com.tabeldata.sipening.auth.usermanage.entity.UmMenu;
import org.springframework.stereotype.Component;

@Component
public class UmMenuMapper {

    public static UmMenu kelola(UmMenuDto.Kelola value) {
        UmMenu entity = new UmMenu();
        entity.setId(value.getId());
        entity.setIdInduk(value.getIdInduk());
        entity.setIdAplikasi(value.getIdAplikasi());
        entity.setKode(value.getKode());
        entity.setNama(value.getNama());
        entity.setKeterangan(value.getKeterangan());
        entity.setNoUrut(value.getNoUrut());
        entity.setLink(value.getLink());
        entity.setIcon(value.getIcon());
        entity.setAktif(value.getAktif());
        return entity;
    }

    public static Integer getIdFromDtRes(UmMenuDto.DtRes value) {
        return value.getId();
    }
}
