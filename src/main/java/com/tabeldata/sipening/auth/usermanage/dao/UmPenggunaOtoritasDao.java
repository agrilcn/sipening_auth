package com.tabeldata.sipening.auth.usermanage.dao;

import com.tabeldata.sipening.auth.usermanage.dto.UmPenggunaOtoritasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UmPenggunaOtoritasDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int save(Integer idPengguna, List<Integer> otoritasList, String nrk) {
        String query = "insert into trsiappenggunaotoritas(\n" +
                "   i_idpengguna,\n" +
                "   i_idotoritas,\n" +
                "   i_pgun_rekam,\n" +
                "   d_pgun_rekam\n" +
                ")\n" +
                "select\n" +
                "   :idPengguna                             as idPengguna,\n" +
                "   i_id                                    as idOtoritas,\n" +
                "   (select i_id from trsiappengguna\n" +
                "    where i_peg_nrk = :nrk)                as idPenggunaRekam,\n" +
                "   now()                                   as tanggalPenggunaRekam\n" +
                "from trsiapotoritas\n" +
                "where i_id in (:otoritasList)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idPengguna", idPengguna);
        parameterSource.addValue("nrk", nrk);
        parameterSource.addValue("otoritasList", otoritasList);

        return jdbcTemplate.update(query, parameterSource);
    }

    public int deleteByIdPengguna(Integer idPengguna) {
        String query = "delete from trsiappenggunaotoritas where i_idpengguna = :idPengguna";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idPengguna", idPengguna);

        return jdbcTemplate.update(query, parameterSource);
    }

    public List<UmPenggunaOtoritasDto.Otor> listOtor(Integer idPengguna, String kondisi) {
        StringBuilder query = new StringBuilder("select\n" +
                "   i_id        as id,\n" +
                "   c_otoritas  as kode,\n" +
                "   n_otoritas  as nama\n" +
                "from trsiapotoritas\n");

        if (kondisi.equals("in")){
            query.append("where i_id in (select i_idotoritas from trsiappenggunaotoritas \n" +
                    "      where i_idpengguna = :idPengguna)");
        } else {
            query.append("where i_id not in (select i_idotoritas from trsiappenggunaotoritas \n" +
                    "             where i_idpengguna = :idPengguna)");
        }

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idPengguna", idPengguna);

        return jdbcTemplate.query(query.toString(), parameterSource, new BeanPropertyRowMapper<>(UmPenggunaOtoritasDto.Otor.class));
    }
}
