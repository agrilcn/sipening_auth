package com.tabeldata.sipening.auth.usermanage.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.tabeldata.sipening.auth.usermanage.dao.UmAplikasiDao;
import com.tabeldata.sipening.auth.usermanage.dto.UmAplikasiDto;
import com.tabeldata.sipening.auth.usermanage.entity.UmAplikasi;
import com.tabeldata.sipening.auth.usermanage.error.NotFoundException;
import com.tabeldata.sipening.auth.usermanage.mapper.UmAplikasiMapper;
import com.tabeldata.sipening.auth.usermanage.mapper.UmOtoritasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UmAplikasiService {

    @Autowired
    private UmAplikasiDao dao;

    @Transactional
    public void save(UmAplikasiDto.Kelola value, String nrk) throws NotFoundException {
        UmAplikasi savedValue = UmAplikasiMapper.kelola(value);
        if (value.getId() == null || value.getId() == 0) {
            dao.save(savedValue, nrk);
        } else {
            findById(value.getId());
            dao.update(savedValue, nrk);
        }
    }

    @Transactional
    public void setAktif(UmAplikasiDto.SetAktif value, String nrk) throws NotFoundException {
        List<Integer> listId = value.getData().stream()
                .map(UmOtoritasMapper::getIdFromDtRes)
                .collect(Collectors.toList());
        dao.setAktif(listId, value.getAktif(), nrk);
    }

    public UmAplikasiDto.Kelola findById(Integer id) throws NotFoundException {
        return dao.findById(id);
    }

    @Transactional
    public void deleteById(Integer id) throws NotFoundException {
        findById(id);
        dao.deleteById(id);
    }

    public DataTablesResponse<UmAplikasiDto.DtRes> datatables(DataTablesRequest<UmAplikasiDto.DtReq> params) {
        List<UmAplikasiDto.DtRes> list = dao.datatables(params);
        Long rowCount = dao.datatables(params.getValue());
        return new DataTablesResponse<>(list, params.getDraw(), rowCount, rowCount);
    }
}
