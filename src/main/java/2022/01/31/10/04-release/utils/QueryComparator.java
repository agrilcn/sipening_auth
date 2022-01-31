package 2022.01.31.10.04-release.utils;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public interface QueryComparator<T> {

    StringBuilder getQuery(T params);

    MapSqlParameterSource getParameters();

}
