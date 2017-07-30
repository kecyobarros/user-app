package br.com.kecyo.userapp.http.data;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@ToString
public class UserDataContract implements Serializable{

    @ApiModelProperty(hidden = true)
    private String id;

    @ApiModelProperty(required = true)
    private String name;

    private List<String> devices;

    private HomeDataContract home;

    private WorkDataContract work;
}
