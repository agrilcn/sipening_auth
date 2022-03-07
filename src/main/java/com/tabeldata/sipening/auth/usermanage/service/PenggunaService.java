package com.tabeldata.sipening.auth.usermanage.service;

import com.tabeldata.sipening.auth.usermanage.dao.PenggunaDao;
import com.tabeldata.sipening.auth.usermanage.dto.PenggunaDto;
import com.tabeldata.sipening.auth.usermanage.error.NotAllowedException;
import com.tabeldata.sipening.auth.usermanage.mapper.PenggunaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PenggunaService {

    @Autowired
    private PenggunaDao dao;

    public PenggunaDto.Info findByNrk(String nrk) {
        return dao.findByNrk(nrk);
    }

    public void isAllowed(String kodeMenu, String nrk) throws NotAllowedException {
        PenggunaDto.AccessMenu value = dao.accessMenu(kodeMenu, nrk);
        if (!value.getIsAllowed()) {
            throw new NotAllowedException(value.getNamaMenu());
        }
    }

    public List<String> listOtoritas(String nrk) {
        return dao.listOtoritas(nrk);
    }

    public List<PenggunaDto.Aplikasi> listAplikasi(String nrk) {
        return dao.listAplikasi(nrk);
    }

    public List<PenggunaDto.AplikasiWithMenu> listMenu(String nrk) {
        List<PenggunaDto.Aplikasi> aplikasiList = dao.listAplikasi(nrk);
        return aplikasiList.stream()
                .map(data -> PenggunaMapper.aplikasiWithMenu(data, listMenuByAplikasi(nrk, data.getId())))
                .collect(Collectors.toList());
    }

    public List<PenggunaDto.Menu> listMenuByAplikasi(String nrk, Integer idAplikasi) {
        return dao.listMenuByAplikasi(nrk, idAplikasi);
    }
}
