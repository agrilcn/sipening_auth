package 2022.01.31.10.04-release.auth.dao;

import 2022.01.31.10.04-release.auth.dto.Authority;

import java.util.List;

public interface AuthorityDao {

    List<Authority> distinctRolesByUsername(String username);
}
