package br.com.kecyo.userapp.http.data;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Set;

@Getter
@ToString
public class UserDataContract implements Serializable{

    @ApiModelProperty(hidden = true)
    private String id;

    @ApiModelProperty(required = true)
    @NotEmpty
    private String name;

    private Set<String> devices;
}
