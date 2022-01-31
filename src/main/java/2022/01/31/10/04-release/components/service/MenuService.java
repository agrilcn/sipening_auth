package 2022.01.31.10.04-release.components.service;

import 2022.01.31.10.04-release.auth.dao.AuthorityDao;
import 2022.01.31.10.04-release.auth.dto.Authority;
import 2022.01.31.10.04-release.components.dao.MenuDao;
import 2022.01.31.10.04-release.components.dto.MenuDto;
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
