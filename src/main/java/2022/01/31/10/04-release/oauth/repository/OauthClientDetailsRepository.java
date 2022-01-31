package 2022.01.31.10.04-release.oauth.repository;

import 2022.01.31.10.04-release.oauth.models.OauthApplication;
import 2022.01.31.10.04-release.oauth.models.OauthClientDetails;
import 2022.01.31.10.04-release.oauth.models.OauthGrantType;
import 2022.01.31.10.04-release.oauth.models.OauthScope;
import org.springframework.dao.EmptyResultDataAccessException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface OauthClientDetailsRepository extends Serializable {

    List<OauthApplication> getApplicationByClientId(String clientId) throws SQLException;

    /**
     * @param clientId
     * @return
     * @throws SQLException
     * @since 1.0.4-release
     */
    @Deprecated
    List<OauthGrantType> getGrantTypeByClientId(String clientId) throws SQLException;

    List<String> getRedirectUrlsByClientId(String clientId) throws SQLException;

    /**
     * @param clientId
     * @return
     * @throws SQLException
     * @since 1.0.4-release
     */
    @Deprecated
    List<OauthScope> getScopesByClientId(String clientId) throws SQLException;

    OauthClientDetails getResourceByClientId(String clientId) throws EmptyResultDataAccessException, SQLException;
}
