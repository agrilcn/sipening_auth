package com.tabeldata.sipening.auth.usermanage.mapper;

import com.tabeldata.sipening.auth.usermanage.dto.UmPenggunaOtoritasDto;
import org.springframework.stereotype.Component;

@Component
public class UmPenggunaOtoritasMapper {

    public static Integer kelola(UmPenggunaOtoritasDto.Otor value) {
        return value.getId();
    }
}
