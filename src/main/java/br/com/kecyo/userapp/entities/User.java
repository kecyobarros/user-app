package br.com.kecyo.userapp.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@ToString
@Document(collection = "users")
public class User implements Serializable {

    @Id
    private String id;

    private String name;

    private List<String> devices;

    private Home home;

    private Work work;
}
