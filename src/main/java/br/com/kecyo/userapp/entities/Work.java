package br.com.kecyo.userapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@AllArgsConstructor
public class Work implements Serializable{

    private double longitude;

    private double latitude;

}


