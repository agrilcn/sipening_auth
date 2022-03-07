package com.tabeldata.sipening.auth.usermanage.dao;

import com.maryanto.dimas.plugins.web.commons.ui.datatables.DataTablesRequest;
import com.maryanto.dimas.plugins.web.commons.ui.datatables.OrderingByColumns;
import com.tabeldata.sipening.auth.usermanage.dto.UmOtoritasDto;
import com.tabeldata.sipening.auth.usermanage.entity.UmOtoritas;
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
public class UmOtoritasDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int save(UmOtoritas value, String nrk) {
        String query = "insert into trsiapotoritas(\n" +
                "   c_otoritas,\n" +
                "   n_otoritas,\n" +
                "   e_otoritas,\n" +
                "   c_aktif,\n" +
                "   i_pgun_rekam,\n" +
                "   d_pgun_rekam\n" +
                ") values(\n" +
                "   :kode,\n" +
                "   :nama,\n" +
                "   :keterangan,\n" +
                "   :aktif,\n" +
                "   (select i_id from trsiappengguna where i_peg_nrk = :nrkPenggunaRekam),\n" +
                "   now()\n" +
                ")";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("kode", value.getKode());
        parameterSource.addValue("nama", value.getNama());
        parameterSource.addValue("keterangan", value.getKeterangan());
        parameterSource.addValue("aktif", value.getAktif());
        parameterSource.addValue("nrkPenggunaRekam", nrk);

        return jdbcTemplate.update(query, parameterSource);
    }

    public int update(UmOtoritas value, String nrk) {
        String query = "update trsiapotoritas\n" +
                "set c_otoritas     = :kode,\n" +
                "   n_otoritas      = :nama,\n" +
                "   e_otoritas      = :keterangan,\n" +
                "   c_aktif         = :aktif,\n" +
                "   i_pgun_ubah     = (select i_id from trsiappengguna where i_peg_nrk = :nrkPenggunaUbah),\n" +
                "   d_pgun_ubah     = now()\n" +
                "where i_id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", value.getId());
        parameterSource.addValue("kode", value.getKode());
        parameterSource.addValue("nama", value.getNama());
        parameterSource.addValue("keterangan", value.getKeterangan());
        parameterSource.addValue("aktif", value.getAktif());
        parameterSource.addValue("nrkPenggunaUbah", nrk);

        return jdbcTemplate.update(query, parameterSource);
    }

    public int setAktif(List<Integer> listId, Boolean aktif, String nrk) {
        String query = "update trsiapotoritas\n" +
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

    public UmOtoritasDto.Kelola findById(Integer id) throws NotFoundException {
        String query = "select\n" +
                "   i_id                        as id,\n" +
                "   c_otoritas                  as kode,\n" +
                "   n_otoritas                  as nama,\n" +
                "   e_otoritas                  as keterangan,\n" +
                "   c_aktif                     as aktif\n" +
                "from trsiapotoritas\n" +
                "where i_id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        try {
            return jdbcTemplate.queryForObject(query, parameterSource, new BeanPropertyRowMapper<>(UmOtoritasDto.Kelola.class));
        } catch (EmptyResultDataAccessException error) {
            throw new NotFoundException("Otoritas Tidak Ditemukan");
        }
    }

    public int deleteById(Integer id) {
        String query = "delete from trsiapotoritas where i_id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        return jdbcTemplate.update(query, parameterSource);
    }

    private static String QUERY_DATATABLES = "select\n" +
            "   i_id                        as id,\n" +
            "   c_otoritas                  as kode,\n" +
            "   n_otoritas                  as nama,\n" +
            "   e_otoritas                  as keterangan,\n" +
            "   c_aktif                     as aktif,\n" +
            "   i_pgun_rekam                as idPenggunaRekam,\n" +
            "   d_pgun_rekam                as tanggalPenggunaRekam,\n" +
            "   i_pgun_ubah                 as idPenggunaUbah,\n" +
            "   d_pgun_ubah                 as tanggalPenggunaUbah\n" +
            "from trsiapotoritas\n" +
            "where 1 = 1\n";

    public List<UmOtoritasDto.DtRes> datatables(DataTablesRequest<UmOtoritasDto.DtReq> params) {
        String baseQuery = QUERY_DATATABLES;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        DatatablesQueryComparator queryComparator = new DatatablesQueryComparator(baseQuery, parameterSource);
        StringBuilder query = queryComparator.getQuery(params.getValue());
        parameterSource = queryComparator.getParameters();

        OrderingByColumns serviceColumn = new OrderingByColumns("id", "kode", "nama", "keterangan", "aktif");
        query.append(serviceColumn.orderBy(params.getColDir(), params.getColOrder()));

        if (params.getLength() > 0) {
            query.append("\nlimit :limit offset :offset");
            parameterSource.addValue("limit", params.getLength());
            parameterSource.addValue("offset", params.getStart());
        }

        return jdbcTemplate.query(query.toString(), parameterSource, new BeanPropertyRowMapper<>(UmOtoritasDto.DtRes.class));
    }

    public Long datatables(UmOtoritasDto.DtReq value) {
        String baseQuery = "select count(*) as rowCount from (\n" +
                QUERY_DATATABLES;

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        DatatablesQueryComparator queryComparator = new DatatablesQueryComparator(baseQuery, parameterSource);
        StringBuilder query = queryComparator.getQuery(value);
        parameterSource = queryComparator.getParameters();

        query.append(") t1");

        return jdbcTemplate.queryForObject(query.toString(), parameterSource, (resultSet, i) -> resultSet.getLong("rowCount"));
    }

    private static class DatatablesQueryComparator implements QueryComparator<UmOtoritasDto.DtReq> {

        private final StringBuilder builder;
        private final MapSqlParameterSource parameterSource;

        public DatatablesQueryComparator(String query, MapSqlParameterSource parameterSource) {
            this.builder = new StringBuilder(query);
            this.parameterSource = parameterSource;
        }

        @Override
        public StringBuilder getQuery(UmOtoritasDto.DtReq param) {

            if (StringUtils.isNotBlank(param.getKode())) {
                this.builder.append(" and lower(c_otoritas) like lower(:kode) ");
                this.parameterSource.addValue("kode", "%" + param.getKode() + "%");
            }

            if (StringUtils.isNotBlank(param.getNama())) {
                this.builder.append(" and lower(n_otoritas) like lower(:nama) ");
                this.parameterSource.addValue("nama", "%" + param.getNama() + "%");
            }

            if (StringUtils.isNotBlank(param.getKeterangan())) {
                this.builder.append(" and lower(e_otoritas) like lower(:keterangan) ");
                this.parameterSource.addValue("keterangan", "%" + param.getKeterangan() + "%");
            }

            return this.builder;
        }

        @Override
        public MapSqlParameterSource getParameters() {
            return this.parameterSource;
        }
    }
}
