package br.com.kecyo.userapp.http.data;


import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class WorkDataContract implements Serializable{

    private double longitude;

    private double latitude;
}
