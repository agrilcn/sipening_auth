package com.tabeldata.sipening.auth.usermanage.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class PublicDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<String> getTahunAnggaran() {
        String query = "select c_tahun_angg\n" +
                "from trtahunangg\n" +
                "where c_aktif = true";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        return jdbcTemplate.query(query, parameterSource, (resultSet, i) -> resultSet.getString("c_tahun_angg"));
    }
}
