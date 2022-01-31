package com.tabeldata.sipening.auth.components.dao;

import com.tabeldata.sipening.auth.auth.dto.Authority;
import com.tabeldata.sipening.auth.components.dto.MenuDto;

import java.util.List;

public interface MenuDao {

    List<MenuDto> getMenuByRolesAndModule(String moduleId, List<Authority> roles);

}
