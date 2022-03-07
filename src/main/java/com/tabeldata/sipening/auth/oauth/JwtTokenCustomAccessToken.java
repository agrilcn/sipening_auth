package com.tabeldata.sipening.auth.oauth;

import com.tabeldata.sipening.auth.usermanage.dto.PenggunaDto;
import com.tabeldata.sipening.auth.usermanage.service.PenggunaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class JwtTokenCustomAccessToken implements TokenEnhancer {

    @Autowired
    private PenggunaService service;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        String username = oAuth2Authentication.getName();
        PenggunaDto.Info penggunaData = service.findByNrk(username);

        Map<String, Object> pengguna = new HashMap<>();
        pengguna.put("id", penggunaData.getId());
        pengguna.put("nrk", penggunaData.getNrk());
        pengguna.put("email", penggunaData.getEmail());
        pengguna.put("noHp", penggunaData.getNoHp());
        pengguna.put("nip", penggunaData.getNip());
        pengguna.put("nama", penggunaData.getNama());
        pengguna.put("jabatan", penggunaData.getJabatan());

        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("pengguna", pengguna);

        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }
}
