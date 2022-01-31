package 2022.01.31.10.04-release.components.dao;

import 2022.01.31.10.04-release.auth.dto.Authority;
import 2022.01.31.10.04-release.components.dto.MenuDto;

import java.util.List;

public interface MenuDao {

    List<MenuDto> getMenuByRolesAndModule(String moduleId, List<Authority> roles);

}
