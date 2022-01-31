package 2022.01.31.10.04-release.oauth.service;

import 2022.01.31.10.04-release.oauth.models.OauthApplication;
import 2022.01.31.10.04-release.oauth.models.OauthClientDetails;
import 2022.01.31.10.04-release.oauth.models.OauthGrantType;
import 2022.01.31.10.04-release.oauth.models.OauthScope;
import 2022.01.31.10.04-release.oauth.repository.OauthClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Service
public class OauthClientDetailsService implements Serializable {

    @Autowired
    private OauthClientDetailsRepository repository;

    public OauthClientDetails findByClientId(String clientId) throws EmptyResultDataAccessException, SQLException {
        return repository.getResourceByClientId(clientId);
    }

    public List<OauthScope> findScopeByClientId(String clientId) throws SQLException {
        return repository.getScopesByClientId(clientId);
    }

    public List<OauthGrantType> findGrantTypeByClientId(String clientId) throws SQLException {
        return repository.getGrantTypeByClientId(clientId);
    }

    public List<OauthApplication> findApplicationByClientId(String clientId) throws SQLException {
        return repository.getApplicationByClientId(clientId);
    }

    public List<String> findRedirectUrlsByClientId(String clientId) throws SQLException {
        return repository.getRedirectUrlsByClientId(clientId);
    }
}
