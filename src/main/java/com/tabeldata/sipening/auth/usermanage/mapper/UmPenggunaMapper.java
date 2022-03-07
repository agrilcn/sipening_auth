package com.tabeldata.sipening.auth.usermanage.mapper;

import com.tabeldata.sipening.auth.usermanage.dto.UmPenggunaDto;
import com.tabeldata.sipening.auth.usermanage.entity.UmPengguna;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class UmPenggunaMapper {

    public static UmPengguna kelola(UmPenggunaDto.Kelola value) {
        UmPengguna entity = new UmPengguna();
        entity.setId(value.getId());
        entity.setNrk(value.getNrk());
        if (value.getSandi() != null) {
            String hashPassword = BCrypt.hashpw(value.getSandi(), BCrypt.gensalt(11));
            entity.setSandi(hashPassword);
        }
        entity.setEmail(value.getEmail());
        entity.setNoHp(value.getNoHp());
        entity.setNip(value.getNip());
        entity.setNama(value.getNama());
        entity.setJabatan(value.getJabatan());
        entity.setAktif(value.getAktif());
        entity.setKunci(value.getKunci());
        return entity;
    }

    public static Integer getIdFromDtRes(UmPenggunaDto.DtRes value) {
        return value.getId();
    }
}
