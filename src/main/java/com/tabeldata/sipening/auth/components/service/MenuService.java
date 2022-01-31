package com.tabeldata.sipening.auth.components.service;

import com.tabeldata.sipening.auth.auth.dao.AuthorityDao;
import com.tabeldata.sipening.auth.auth.dto.Authority;
import com.tabeldata.sipening.auth.components.dao.MenuDao;
import com.tabeldata.sipening.auth.components.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private AuthorityDao authorityDao;

    public List<MenuDto> findAllByModuleId(String username, String moduleId) {
        List<Authority> authorities = authorityDao.distinctRolesByUsername(username);
        return menuDao.getMenuByRolesAndModule(moduleId, authorities);
    }
}
