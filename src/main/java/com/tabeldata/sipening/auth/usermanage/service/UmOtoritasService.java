package com.tabeldata.sipening.auth.usermanage.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.tabeldata.sipening.auth.usermanage.dao.UmOtoritasDao;
import com.tabeldata.sipening.auth.usermanage.dto.UmOtoritasDto;
import com.tabeldata.sipening.auth.usermanage.entity.UmOtoritas;
import com.tabeldata.sipening.auth.usermanage.error.NotFoundException;
import com.tabeldata.sipening.auth.usermanage.mapper.UmOtoritasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UmOtoritasService {

    @Autowired
    private UmOtoritasDao dao;

    @Transactional
    public void save(UmOtoritasDto.Kelola value, String nrk) throws NotFoundException {
        UmOtoritas savedValue = UmOtoritasMapper.kelola(value);
        if (value.getId() == null || value.getId() == 0) {
            dao.save(savedValue, nrk);
        } else {
            findById(value.getId());
            dao.update(savedValue, nrk);
        }
    }

    @Transactional
    public void setAktif(UmOtoritasDto.SetAktif value, String nrk) throws NotFoundException {
        List<Integer> listId = value.getData().stream()
                .map(UmOtoritasMapper::getIdFromDtRes)
                .collect(Collectors.toList());
        dao.setAktif(listId, value.getAktif(), nrk);
    }

    public UmOtoritasDto.Kelola findById(Integer id) throws NotFoundException {
        return dao.findById(id);
    }

    @Transactional
    public void deleteById(Integer id) throws NotFoundException {
        findById(id);
        dao.deleteById(id);
    }

    public DataTablesResponse<UmOtoritasDto.DtRes> datatables(DataTablesRequest<UmOtoritasDto.DtReq> params) {
        List<UmOtoritasDto.DtRes> list = dao.datatables(params);
        Long rowCount = dao.datatables(params.getValue());
        return new DataTablesResponse<>(list, params.getDraw(), rowCount, rowCount);
    }
}
