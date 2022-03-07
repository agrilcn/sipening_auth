package com.tabeldata.sipening.auth.usermanage.service;

import com.tabeldata.sipening.auth.usermanage.dao.UmOtoritasAplikasiDao;
import com.tabeldata.sipening.auth.usermanage.dto.UmOtoritasAplikasiDto;
import com.tabeldata.sipening.auth.usermanage.mapper.UmOtoritasAplikasiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UmOtoritasAplikasiService {

    @Autowired
    private UmOtoritasAplikasiDao dao;

    @Transactional
    public void save(UmOtoritasAplikasiDto.Kelola value, String nrk) {
        dao.deleteByIdOtoritas(value.getIdOtoritas());
        if (value.getAppList().size() != 0) {
            List<Integer> listIdAplikasi = value.getAppList().stream()
                    .map(UmOtoritasAplikasiMapper::kelola)
                    .collect(Collectors.toList());
            dao.save(value.getIdOtoritas(), listIdAplikasi, nrk);
        }
    }

    public UmOtoritasAplikasiDto.ListApp listApp(Integer idOtoritas) {
        List<UmOtoritasAplikasiDto.App> listIn = dao.listApp(idOtoritas, "in");
        List<UmOtoritasAplikasiDto.App> listNotIn = dao.listApp(idOtoritas, "notIn");
        UmOtoritasAplikasiDto.ListApp   listApp = new UmOtoritasAplikasiDto.ListApp();
        listApp.setIn(listIn);
        listApp.setNotIn(listNotIn);
        return listApp;
    }
}
