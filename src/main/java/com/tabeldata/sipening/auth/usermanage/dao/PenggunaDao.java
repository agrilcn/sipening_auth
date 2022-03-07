package com.tabeldata.sipening.auth.usermanage.dao;

import com.tabeldata.sipening.auth.usermanage.dto.PenggunaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PenggunaDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public PenggunaDto.Info findByNrk(String nrk) {
        String query = "select\n" +
                "   i_id                        as id,\n" +
                "   i_peg_nrk                   as nrk,\n" +
                "   n_email                     as email,\n" +
                "   n_hpno                      as noHp,\n" +
                "   i_peg_nip                   as nip,\n" +
                "   n_peg                       as nama,\n" +
                "   n_peg_jabatan               as jabatan\n" +
                "from trsiappengguna\n" +
                "where i_peg_nrk = :nrk";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nrk", nrk);

        return jdbcTemplate.queryForObject(query, parameterSource, new BeanPropertyRowMapper<>(PenggunaDto.Info.class));
    }

    public PenggunaDto.AccessMenu accessMenu(String kodeMenu, String nrk) {
        String query = "select\n" +
                "   exists(select 1 from trsiappengguna pgun\n" +
                "             left join trsiappenggunaotoritas pgunotor on pgun.i_id = pgunotor.i_idpengguna\n" +
                "             left join trsiapotoritasmenu otormenu on pgunotor.i_idotoritas = otormenu.i_idotoritas\n" +
                "             left join trsiapmenu menu on otormenu.i_idmenu = menu.i_id\n" +
                "          where menu.c_menu = :kodeMenu\n" +
                "          and pgun.i_peg_nrk = :nrk)                           as isAllowed,\n" +
                "   (select n_menu from trsiapmenu where c_menu = :kodeMenu)    as namaMenu";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("kodeMenu", kodeMenu);
        parameterSource.addValue("nrk", nrk);

        return jdbcTemplate.queryForObject(query, parameterSource,  new BeanPropertyRowMapper<>(PenggunaDto.AccessMenu.class));
    }

    public List<String> listOtoritas(String nrk) {
        String query = "select otor.c_otoritas as kodeOtoritas\n" +
                "from\n" +
                "   trsiappengguna pgun\n" +
                "   left join trsiappenggunaotoritas pgunotor on pgun.i_id = pgunotor.i_idpengguna\n" +
                "   left join trsiapotoritas otor on pgunotor.i_idotoritas = otor.i_id\n" +
                "where\n" +
                "   otor.c_aktif = true\n" +
                "   and pgun.i_peg_nrk = :nrk";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nrk", nrk);

        return jdbcTemplate.query(query, parameterSource, (resultSet, i) -> resultSet.getString("kodeOtoritas"));
    }

    public List<PenggunaDto.Aplikasi> listAplikasi(String nrk) {
        String query = "select distinct\n" +
                "   app.i_id        as id, \n" +
                "   app.c_aplikasi  as kode,\n" +
                "   app.n_aplikasi  as nama,\n" +
                "   app.n_link      as link,\n" +
                "   app.no_urut     as noUrut\n" +
                "from trsiappengguna pgun\n" +
                "   left join trsiappenggunaotoritas pgunotor on pgun.i_id = pgunotor.i_idpengguna\n" +
                "   left join trsiapotoritasaplikasi otorapp on pgunotor.i_idotoritas = otorapp.i_idotoritas\n" +
                "   left join trsiapaplikasi app on otorapp.i_idaplikasi = app.i_id\n" +
                "where\n" +
                "   app.c_aktif = true\n" +
                "   and pgun.i_peg_nrk = :nrk\n" +
                "order by noUrut asc";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nrk", nrk);

        return jdbcTemplate.query(query, parameterSource, new BeanPropertyRowMapper<>(PenggunaDto.Aplikasi.class));
    }

    public List<PenggunaDto.Menu> listMenuByAplikasi(String nrk, Integer idAplikasi) {
        String query = "select distinct\n" +
                "   menu.i_id        as id,\n" +
                "   menu.i_idinduk   as idInduk,\n" +
                "   menu.n_menu      as nama,\n" +
                "   menu.e_menu      as keterangan,\n" +
                "   menu.n_link      as link,\n" +
                "   menu.icon        as icon,\n" +
                "   (select count(*) \n" +
                "    from trsiapmenu sub \n" +
                "    where sub.i_idinduk = menu.i_id) as countSubmenu,\n" +
                "   menu.no_urut     as noUrut\n" +
                "from trsiappengguna pgun\n" +
                "   left join trsiappenggunaotoritas pgunotor on pgun.i_id = pgunotor.i_idpengguna\n" +
                "   left join trsiapotoritasmenu otormenu on pgunotor.i_idotoritas = otormenu.i_idotoritas\n" +
                "   left join trsiapmenu menu on otormenu.i_idmenu = menu.i_id\n" +
                "where\n" +
                "   menu.c_aktif = true\n" +
                "   and menu.i_idinduk is null\n" +
                "   and menu.i_idaplikasi = :idAplikasi\n" +
                "   and pgun.i_peg_nrk = :nrk\n" +
                "order by noUrut asc";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nrk", nrk);
        parameterSource.addValue("idAplikasi", idAplikasi);

        return jdbcTemplate.query(query, parameterSource, (resultSet, i) -> {
            PenggunaDto.Menu data = new PenggunaDto.Menu();
            data.setId(resultSet.getInt("id"));
            data.setIdInduk(resultSet.getInt("idInduk"));
            data.setNama(resultSet.getString("nama"));
            data.setKeterangan(resultSet.getString("keterangan"));
            data.setLink(resultSet.getString("link"));
            data.setIcon(resultSet.getString("icon"));
            if (resultSet.getInt("countSubmenu") > 0) {
                data.setSubMenu(listSubMenu(nrk, data.getId()));
            }
            return data;
        });
    }

    public List<PenggunaDto.SubMenu> listSubMenu(String nrk, Integer idMenu) {
        String query = "select distinct\n" +
                "   menu.i_id        as id,\n" +
                "   menu.i_idinduk   as idInduk,\n" +
                "   menu.n_menu      as nama,\n" +
                "   menu.e_menu      as keterangan,\n" +
                "   menu.n_link      as link,\n" +
                "   menu.icon        as icon,\n" +
                "   menu.no_urut     as noUrut\n" +
                "from trsiappengguna pgun\n" +
                "   left join trsiappenggunaotoritas pgunotor on pgun.i_id = pgunotor.i_idpengguna\n" +
                "   left join trsiapotoritasmenu otormenu on pgunotor.i_idotoritas = otormenu.i_idotoritas\n" +
                "   left join trsiapmenu menu on otormenu.i_idmenu = menu.i_id\n" +
                "where\n" +
                "   menu.c_aktif = true\n" +
                "   and menu.i_idinduk = :idMenu\n" +
                "   and pgun.i_peg_nrk = :nrk\n" +
                "order by noUrut asc";

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nrk", nrk);
        parameterSource.addValue("idMenu", idMenu);

        return jdbcTemplate.query(query, parameterSource, new BeanPropertyRowMapper<>(PenggunaDto.SubMenu.class));
    }
}
