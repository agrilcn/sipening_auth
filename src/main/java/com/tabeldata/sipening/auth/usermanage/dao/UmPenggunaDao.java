package com.tabeldata.sipening.auth.usermanage.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.OrderingByColumns;
import com.tabeldata.sipening.auth.usermanage.dto.UmPenggunaDto;
import com.tabeldata.sipening.auth.usermanage.entity.UmPengguna;
import com.tabeldata.sipening.auth.usermanage.error.NotFoundException;
import com.tabeldata.sipening.auth.utils.QueryComparator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UmPenggunaDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int save(UmPengguna value, String nrk) {
        String query = "insert into trsiappengguna(\n" +
                "   i_peg_nrk,\n" +
                "   i_sandi,\n" +
                "   n_email,\n" +
                "   n_hpno,\n" +
                "   i_peg_nip,\n" +
                "   n_peg,\n" +
                "   n_peg_jabatan,\n" +
                "   d_sandi_expired,\n" +
                "   c_lock,\n" +
                "   c_aktif,\n" +
                "   i_pgun_rekam,\n" +
                "   d_pgun_rekam\n" +
                ") VALUES(\n" +
                "   :nrk,\n" +
                "   :sandi,\n" +
                "   :email,\n" +
                "   :noHp,\n" +
                "   :nip,\n" +
                "   :nama,\n" +
                "   :jabatan,\n" +
                "   (now() + INTERVAL '1 MONTH'),\n" +
                "   :kunci,\n" +
                "   :aktif,\n" +
                "   (select i_id from trsiappengguna where i_peg_nrk = :nrkPenggunaRekam),\n" +
                "   now()\n" +
                ")";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("nrk", value.getNrk());
        parameterSource.addValue("sandi", value.getSandi());
        parameterSource.addValue("email", value.getEmail());
        parameterSource.addValue("noHp", value.getNoHp());
        parameterSource.addValue("nip", value.getNip());
        parameterSource.addValue("nama", value.getNama());
        parameterSource.addValue("jabatan", value.getJabatan());
        parameterSource.addValue("kunci", value.getKunci());
        parameterSource.addValue("aktif", value.getAktif());
        parameterSource.addValue("nrkPenggunaRekam", nrk);

        return jdbcTemplate.update(query, parameterSource);
    }

    public int update(UmPengguna value, String nrk) {
        String query = "update trsiappengguna\n" +
                "set\n" +
                "   i_peg_nrk       = :nrk,\n" +
                "   n_email         = :email,\n" +
                "   n_hpno          = :noHp,\n" +
                "   i_peg_nip       = :nip,\n" +
                "   n_peg           = :nama,\n" +
                "   n_peg_jabatan   = :jabatan,\n" +
                "   c_lock          = :kunci,\n" +
                "   c_aktif         = :aktif,\n" +
                "   i_pgun_ubah     = (select i_id from trsiappengguna where i_peg_nrk = :nrkPenggunaUbah),\n" +
                "   d_pgun_ubah     = now()\n" +
                "where i_id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", value.getId());
        parameterSource.addValue("nrk", value.getNrk());
        parameterSource.addValue("email", value.getEmail());
        parameterSource.addValue("noHp", value.getNoHp());
        parameterSource.addValue("nip", value.getNip());
        parameterSource.addValue("nama", value.getNama());
        parameterSource.addValue("jabatan", value.getJabatan());
        parameterSource.addValue("kunci", value.getKunci());
        parameterSource.addValue("aktif", value.getAktif());
        parameterSource.addValue("nrkPenggunaUbah", nrk);

        return jdbcTemplate.update(query, parameterSource);
    }

    public int changePassword(Integer id, String sandi, String nrk) {
        String query = "update trsiappengguna\n" +
                "set\n" +
                "   i_sandi         = :sandi,\n" +
                "   d_sandi_expired = (now() + INTERVAL '1 MONTH'),\n" +
                "   i_pgun_ubah     = (select i_id from trsiappengguna where i_peg_nrk = :nrkPenggunaUbah),\n" +
                "   d_pgun_ubah     = now()\n" +
                "where i_id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        parameterSource.addValue("sandi",sandi);
        parameterSource.addValue("nrkPenggunaUbah",nrk);

        return jdbcTemplate.update(query, parameterSource);
    }

    public int setLock(List<Integer> listId, Boolean kunci, String nrk) {
        String query = "update trsiappengguna\n" +
                "set c_lock        = :kunci,\n" +
                "   i_pgun_ubah     = (select i_id from trsiappengguna where i_peg_nrk = :nrkPenggunaUbah),\n" +
                "   d_pgun_ubah     = now()\n" +
                "where i_id in (:listId)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("kunci", kunci);
        parameterSource.addValue("nrkPenggunaUbah", nrk);
        parameterSource.addValue("listId", listId);

        return jdbcTemplate.update(query, parameterSource);
    }

    public int setAktif(List<Integer> listId, Boolean aktif, String nrk) {
        String query = "update trsiappengguna\n" +
                "set c_aktif        = :aktif,\n" +
                "   i_pgun_ubah     = (select i_id from trsiappengguna where i_peg_nrk = :nrkPenggunaUbah),\n" +
                "   d_pgun_ubah     = now()\n" +
                "where i_id in (:listId)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("aktif", aktif);
        parameterSource.addValue("nrkPenggunaUbah", nrk);
        parameterSource.addValue("listId", listId);

        return jdbcTemplate.update(query, parameterSource);
    }

    public UmPenggunaDto.Kelola findById(Integer id) throws NotFoundException {
        String query = "select\n" +
                "   i_id                        as id,\n" +
                "   i_peg_nrk                   as nrk,\n" +
                "   null                        as sandi,\n" +
                "   n_email                     as email,\n" +
                "   n_hpno                      as noHp,\n" +
                "   i_peg_nip                   as nip,\n" +
                "   n_peg                       as nama,\n" +
                "   n_peg_jabatan               as jabatan,\n" +
                "   c_aktif                     as aktif,\n" +
                "   c_lock                      as kunci\n" +
                "from trsiappengguna\n" +
                "where i_id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        try {
            return jdbcTemplate.queryForObject(query, parameterSource, new BeanPropertyRowMapper<>(UmPenggunaDto.Kelola.class));
        } catch (EmptyResultDataAccessException error) {
            throw new NotFoundException("Pengguna Tidak Ditemukan");
        }
    }

    public int deleteById(Integer id) {
        String query = "delete from trsiappengguna where i_id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        return jdbcTemplate.update(query, parameterSource);
    }

    private static String QUERY_DATATABLES = "select\n" +
            "   i_id                        as id,\n" +
            "   i_peg_nrk                   as nrk,\n" +
            "   n_email                     as email,\n" +
            "   n_hpno                      as noHp,\n" +
            "   i_peg_nip                   as nip,\n" +
            "   n_peg                       as nama,\n" +
            "   n_peg_jabatan               as jabatan,\n" +
            "   c_aktif                     as aktif,\n" +
            "   c_lock                      as kunci,\n" +
            "   i_pgun_rekam                as idPenggunaRekam,\n" +
            "   d_pgun_rekam                as tanggalPenggunaRekam,\n" +
            "   i_pgun_ubah                 as idPenggunaUbah,\n" +
            "   d_pgun_ubah                 as tanggalPenggunaUbah\n" +
            "from trsiappengguna\n" +
            "where 1 = 1\n";

    public List<UmPenggunaDto.DtRes> datatables(DataTablesRequest<UmPenggunaDto.DtReq> params) {
        String baseQuery = QUERY_DATATABLES;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        DatatablesQueryComparator queryComparator = new DatatablesQueryComparator(baseQuery, parameterSource);
        StringBuilder query = queryComparator.getQuery(params.getValue());
        parameterSource = queryComparator.getParameters();

        OrderingByColumns serviceColumn = new OrderingByColumns("id", "nama", "email", "nip", "nrk", "aktif", "kunci");
        query.append(serviceColumn.orderBy(params.getColDir(), params.getColOrder()));

        if (params.getLength() > 0) {
            query.append("\nlimit :limit offset :offset");
            parameterSource.addValue("limit", params.getLength());
            parameterSource.addValue("offset", params.getStart());
        }

        return jdbcTemplate.query(query.toString(), parameterSource, new BeanPropertyRowMapper<>(UmPenggunaDto.DtRes.class));
    }

    public Long datatables(UmPenggunaDto.DtReq value) {
        String baseQuery = "select count(*) as rowCount from (\n" +
                QUERY_DATATABLES;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        DatatablesQueryComparator queryComparator = new DatatablesQueryComparator(baseQuery, parameterSource);
        StringBuilder query = queryComparator.getQuery(value);
        parameterSource = queryComparator.getParameters();

        query.append(") t1");

        return jdbcTemplate.queryForObject(query.toString(), parameterSource, (resultSet, i) -> resultSet.getLong("rowCount"));
    }

    private static class DatatablesQueryComparator implements QueryComparator<UmPenggunaDto.DtReq> {

        private final StringBuilder builder;
        private final MapSqlParameterSource parameterSource;

        public DatatablesQueryComparator(String query, MapSqlParameterSource parameterSource) {
            this.builder = new StringBuilder(query);
            this.parameterSource = parameterSource;
        }

        @Override
        public StringBuilder getQuery(UmPenggunaDto.DtReq param) {

            if (StringUtils.isNotBlank(param.getNrk())) {
                this.builder.append(" and lower(i_peg_nrk) like lower(:nrk) ");
                this.parameterSource.addValue("nrk", "%" + param.getNrk() + "%");
            }

            if (StringUtils.isNotBlank(param.getEmail())) {
                this.builder.append(" and lower(n_email) like lower(:email) ");
                this.parameterSource.addValue("email", "%" + param.getEmail() + "%");
            }

            if (StringUtils.isNotBlank(param.getNip())) {
                this.builder.append(" and i_peg_nip like :nip ");
                this.parameterSource.addValue("nip", "%" + param.getNip() + "%");
            }

            if (StringUtils.isNotBlank(param.getNama())) {
                this.builder.append(" and lower(n_peg) like lower(:nama) ");
                this.parameterSource.addValue("nama", "%" + param.getNama() + "%");
            }

            return this.builder;
        }

        @Override
        public MapSqlParameterSource getParameters() {
            return this.parameterSource;
        }
    }
}
