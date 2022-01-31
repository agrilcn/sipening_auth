package com.tabeldata.sipening.auth.auth.dao;

import com.tabeldata.sipening.auth.auth.dto.Authority;

import java.util.List;

public interface AuthorityDao {

    List<Authority> distinctRolesByUsername(String username);
}
