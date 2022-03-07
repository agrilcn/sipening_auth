package com.tabeldata.sipening.auth.usermanage.service;

import com.tabeldata.sipening.auth.usermanage.dao.UmPenggunaOtoritasDao;
import com.tabeldata.sipening.auth.usermanage.dto.UmPenggunaOtoritasDto;
import com.tabeldata.sipening.auth.usermanage.mapper.UmPenggunaOtoritasMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UmPenggunaOtoritasService {

    @Autowired
    private UmPenggunaOtoritasDao dao;

    @Transactional
    public void save(UmPenggunaOtoritasDto.Kelola value, String nrk) {
        dao.deleteByIdPengguna(value.getIdPengguna());
        if (value.getOtoritasList().size() != 0) {
            List<Integer> listIdOtoritas = value.getOtoritasList().stream()
                    .map(UmPenggunaOtoritasMapper::kelola)
                    .collect(Collectors.toList());
            dao.save(value.getIdPengguna(), listIdOtoritas, nrk);
        }
    }

    public UmPenggunaOtoritasDto.ListOtor  listOtor(Integer idPengguna) {
        List<UmPenggunaOtoritasDto.Otor> listIn = dao.listOtor(idPengguna, "in");
        List<UmPenggunaOtoritasDto.Otor> listNotIn = dao.listOtor(idPengguna, "notIn");
        UmPenggunaOtoritasDto.ListOtor listOtor = new UmPenggunaOtoritasDto.ListOtor();
        listOtor.setIn(listIn);
        listOtor.setNotIn(listNotIn);
        return listOtor;
    }
}
