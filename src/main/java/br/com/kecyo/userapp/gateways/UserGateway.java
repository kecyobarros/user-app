package br.com.kecyo.userapp.gateways;

import br.com.kecyo.userapp.entities.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserGateway {

    Page<User> findAll(int page);

    Optional<User> findById(final String id);

    void save(final User device);
}
