
package com.tabeldata.sipening.auth.usermanage.mapper;

import com.tabeldata.sipening.auth.usermanage.dto.UmOtoritasMenuDto;
import org.springframework.stereotype.Component;

@Component
public class UmOtoritasMenuMapper {

    public static Integer kelola(UmOtoritasMenuDto.Menu value) {
        return value.getId();
    }
}
