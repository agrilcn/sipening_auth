package com.tabeldata.sipening.auth.usermanage.dao;

import com.tabeldata.sipening.auth.usermanage.dto.UmOtoritasAplikasiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UmOtoritasAplikasiDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int save(Integer idOtoritas, List<Integer> aplikasiList, String nrk) {
        String query = "insert into trsiapotoritasaplikasi(\n" +
                "   i_idotoritas,\n" +
                "   i_idaplikasi,\n" +
                "   i_pgun_rekam,\n" +
                "   d_pgun_rekam\n" +
                ")\n" +
                "select\n" +
                "   :idOtoritas                             as idOtoritas,\n" +
                "   i_id                                    as idAplikasi,\n" +
                "   (select i_id from trsiappengguna\n" +
                "    where i_peg_nrk = :nrk)                as idPenggunaRekam,\n" +
                "   now()                                   as tanggalPenggunaRekam\n" +
                "from trsiapaplikasi\n" +
                "where i_id in (:aplikasiList)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idOtoritas", idOtoritas);
        parameterSource.addValue("nrk", nrk);
        parameterSource.addValue("aplikasiList", aplikasiList);

        return jdbcTemplate.update(query, parameterSource);
    }

    public int deleteByIdOtoritas(Integer idOtoritas) {
        String query = "delete from trsiapotoritasaplikasi where i_idotoritas = :idOtoritas";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idOtoritas", idOtoritas);

        return jdbcTemplate.update(query, parameterSource);
    }

    public List<UmOtoritasAplikasiDto.App> listApp(Integer idOtoritas, String kondisi) {
        StringBuilder query = new StringBuilder("select\n" +
                "   i_id        as id,\n" +
                "   c_aplikasi  as kode,\n" +
                "   n_aplikasi  as nama\n" +
                "from trsiapaplikasi\n");

        if (kondisi.equals("in")){
            query.append("where i_id in (select i_idaplikasi from trsiapotoritasaplikasi \n" +
                    "      where i_idotoritas = :idOtoritas)");
        } else {
            query.append("where i_id not in (select i_idaplikasi from trsiapotoritasaplikasi \n" +
                    "             where i_idotoritas = :idOtoritas)");
        }

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idOtoritas", idOtoritas);

        return jdbcTemplate.query(query.toString(), parameterSource, new BeanPropertyRowMapper<>(UmOtoritasAplikasiDto.App.class));
    }
}
