package 2022.01.31.10.04-release.oauth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OauthAccessTokenExtended {

    private String username;
    private String clientId;
    private String ipAddress;
    private String accessToken;
    private Timestamp loginAt;
    private Timestamp expiredAt;
}