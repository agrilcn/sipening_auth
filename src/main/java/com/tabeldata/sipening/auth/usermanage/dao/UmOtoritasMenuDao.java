package com.tabeldata.sipening.auth.usermanage.dao;

import com.tabeldata.sipening.auth.usermanage.dto.UmOtoritasMenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UmOtoritasMenuDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int save(Integer idOtoritas, List<Integer> menuList, String nrk) {
        String query = "insert into trsiapotoritasmenu(\n" +
                "   i_idotoritas,\n" +
                "   i_idmenu,\n" +
                "   i_pgun_rekam,\n" +
                "   d_pgun_rekam\n" +
                ")\n" +
                "select\n" +
                "   :idOtoritas                             as idOtoritas,\n" +
                "   i_id                                    as idMenu,\n" +
                "   (select i_id from trsiappengguna\n" +
                "    where i_peg_nrk = :nrk)                as idPenggunaRekam,\n" +
                "   now()                                   as tanggalPenggunaRekam\n" +
                "from trsiapmenu\n" +
                "where i_id in (:menuList)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idOtoritas", idOtoritas);
        parameterSource.addValue("nrk", nrk);
        parameterSource.addValue("menuList", menuList);

        return jdbcTemplate.update(query, parameterSource);
    }

    public int deleteByIdOtoritas(Integer idOtoritas, Integer idAplikasi) {
        String query = "delete from trsiapotoritasmenu \n" +
                "where i_idotoritas = :idOtoritas \n" +
                "     and i_idmenu in (select i_id from trsiapmenu \n" +
                "                      where i_idaplikasi = :idAplikasi)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idOtoritas", idOtoritas);
        parameterSource.addValue("idAplikasi", idAplikasi);

        return jdbcTemplate.update(query, parameterSource);
    }

    public List<UmOtoritasMenuDto.Menu> listMenu(Integer idOtoritas, Integer idAplikasi, String kondisi) {
        StringBuilder query = new StringBuilder("select\n" +
                "   i_id        as id,\n" +
                "   C_menu      as kode,\n" +
                "   N_menu      as nama,\n" +
                "   (case \n" +
                "        when i_idinduk is not null then (select sub.no_urut from trsiapmenu sub where sub.i_id = main.i_idinduk)\n" +
                "        else no_urut\n" +
                "    END)       as noUrutInduk,\n" +
                "   (case \n" +
                "        when i_idinduk is not null then 1\n" +
                "        else 0\n" +
                "    END)       as isSubMenu\n" +
                "from trsiapmenu main\n" +
                "where\n" +
                "   i_idaplikasi = :idAplikasi\n");

        if (kondisi.equals("in")){
            query.append("   and i_id in (select i_idmenu from trsiapotoritasmenu \n" +
                    "      where i_idotoritas = :idOtoritas)\n");
        } else {
            query.append("   and i_id not in (select i_idmenu from trsiapotoritasmenu \n" +
                    "             where i_idotoritas = :idOtoritas)\n");
        }
        query.append("order by noUrutInduk asc, isSubMenu asc");

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("idAplikasi", idAplikasi);
        parameterSource.addValue("idOtoritas", idOtoritas);

        return jdbcTemplate.query(query.toString(), parameterSource, new BeanPropertyRowMapper<>(UmOtoritasMenuDto.Menu.class));
    }
}
