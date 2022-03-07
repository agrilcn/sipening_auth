package com.tabeldata.sipening.auth.usermanage.mapper;

import com.tabeldata.sipening.auth.usermanage.dto.PenggunaDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PenggunaMapper {

    public static PenggunaDto.AplikasiWithMenu aplikasiWithMenu(PenggunaDto.Aplikasi value, List<PenggunaDto.Menu> menu) {
        PenggunaDto.AplikasiWithMenu data = new PenggunaDto.AplikasiWithMenu();
        data.setId(value.getId());
        data.setKode(value.getKode());
        data.setNama(value.getNama());
        data.setLink(value.getLink());
        data.setMenu(menu);
        return data;
    }
}
