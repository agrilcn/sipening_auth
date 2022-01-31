package 2022.01.31.10.04-release.oauth.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OauthGrantType implements Serializable {

    private Integer id;
    private String name;
    private String descrption;
}
