package com.tabeldata.sipening.auth.usermanage.mapper;

import com.tabeldata.sipening.auth.usermanage.dto.UmOtoritasAplikasiDto;
import org.springframework.stereotype.Component;

@Component
public class UmOtoritasAplikasiMapper {

    public static Integer kelola(UmOtoritasAplikasiDto.App value) {
        return value.getId();
    }
}
