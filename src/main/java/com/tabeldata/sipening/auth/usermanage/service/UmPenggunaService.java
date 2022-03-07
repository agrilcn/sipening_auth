package com.tabeldata.sipening.auth.usermanage.service;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesResponse;
import com.tabeldata.sipening.auth.usermanage.dao.UmPenggunaDao;
import com.tabeldata.sipening.auth.usermanage.dto.UmPenggunaDto;
import com.tabeldata.sipening.auth.usermanage.entity.UmPengguna;
import com.tabeldata.sipening.auth.usermanage.error.NotFoundException;
import com.tabeldata.sipening.auth.usermanage.mapper.UmOtoritasMapper;
import com.tabeldata.sipening.auth.usermanage.mapper.UmPenggunaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UmPenggunaService {

    @Autowired
    private UmPenggunaDao dao;

    @Transactional
    public void save(UmPenggunaDto.Kelola value, String nrk) throws NotFoundException {
        UmPengguna savedValue = UmPenggunaMapper.kelola(value);
        if (value.getId() == null || value.getId() == 0) {
            dao.save(savedValue, nrk);
        } else {
            findById(value.getId());
            dao.update(savedValue, nrk);
        }
    }

    @Transactional
    public void changePassword(UmPenggunaDto.ChangePassword value, String nrk) throws NotFoundException {
        if (value.getSandi().matches(value.getKonfirmasiSandi())) {
            findById(value.getId());
            String hashPassword = BCrypt.hashpw(value.getSandi(), BCrypt.gensalt(11));
            dao.changePassword(value.getId(), hashPassword, nrk);
        } else {
            throw new NotFoundException("Sandi Tidak Sama Dengan Konfirmasi Sandi");
        }
    }

    @Transactional
    public void setLock(UmPenggunaDto.SetLock value, String nrk) {
        List<Integer> listId = value.getData().stream()
                .map(UmOtoritasMapper::getIdFromDtRes)
                .collect(Collectors.toList());
        dao.setLock(listId, value.getKunci(), nrk);
    }

    @Transactional
    public void setAktif(UmPenggunaDto.SetAktif value, String nrk) {
        List<Integer> listId = value.getData().stream()
                .map(UmOtoritasMapper::getIdFromDtRes)
                .collect(Collectors.toList());
        dao.setAktif(listId, value.getAktif(), nrk);
    }

    public UmPenggunaDto.Kelola findById(Integer id) throws NotFoundException {
        return dao.findById(id);
    }

    @Transactional
    public void deleteById(Integer id) throws NotFoundException {
        findById(id);
        dao.deleteById(id);
    }

    public DataTablesResponse<UmPenggunaDto.DtRes> datatables(DataTablesRequest<UmPenggunaDto.DtReq> params) {
        List<UmPenggunaDto.DtRes> list = dao.datatables(params);
        Long rowCount = dao.datatables(params.getValue());
        return new DataTablesResponse<>(list, params.getDraw(), rowCount, rowCount);
    }
}
