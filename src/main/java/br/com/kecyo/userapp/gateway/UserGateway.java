package br.com.kecyo.userapp.gateway;

import br.com.kecyo.userapp.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserGateway {

    List<User> findAll();

    Optional<User> findById(final String id);

    void save(final User device);
}
