package br.com.kecyo.userapp.gateway.impl;

import br.com.kecyo.userapp.entities.User;
import br.com.kecyo.userapp.gateway.UserGateway;
import br.com.kecyo.userapp.gateway.repository.mongo.UserRespository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway{

    private final UserRespository repository;

    @Override
    public List<User> findAll() {
        log.info("Gateway FindAll");
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(final String id) {
        log.info("Gateway FindById {}", id);
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public void save(final User device) {
        log.info("Gateway save {}", device);
        repository.save(device);
    }
}
