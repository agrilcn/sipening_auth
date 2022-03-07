package com.tabeldata.sipening.auth.usermanage.service;

import com.tabeldata.sipening.auth.usermanage.dao.UmOtoritasMenuDao;
import com.tabeldata.sipening.auth.usermanage.dto.UmOtoritasMenuDto;
import com.tabeldata.sipening.auth.usermanage.mapper.UmOtoritasMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UmOtoritasMenuService {

    @Autowired
    private UmOtoritasMenuDao dao;

    @Transactional
    public void save(UmOtoritasMenuDto.Kelola value, String nrk) {
        dao.deleteByIdOtoritas(value.getIdOtoritas(), value.getIdAplikasi());
        if (value.getMenuList().size() != 0) {
            List<Integer> listIdAplikasi = value.getMenuList().stream()
                    .map(UmOtoritasMenuMapper::kelola)
                    .collect(Collectors.toList());
            dao.save(value.getIdOtoritas(), listIdAplikasi, nrk);
        }
    }

    public UmOtoritasMenuDto.ListMenu listMenu(Integer idOtoritas, Integer idAplikasi) {
        List<UmOtoritasMenuDto.Menu> listIn = dao.listMenu(idOtoritas, idAplikasi, "in");
        List<UmOtoritasMenuDto.Menu> listNotIn = dao.listMenu(idOtoritas, idAplikasi, "notIn");
        UmOtoritasMenuDto.ListMenu   listMenu = new UmOtoritasMenuDto.ListMenu();
        listMenu.setIn(listIn);
        listMenu.setNotIn(listNotIn);
        return listMenu;
    }
}
