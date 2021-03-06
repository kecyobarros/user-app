package br.com.kecyo.userapp.gateways.impl;

import br.com.kecyo.userapp.entities.User;
import br.com.kecyo.userapp.gateways.UserGateway;
import br.com.kecyo.userapp.gateways.repository.mongo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway{

    private final UserRepository repository;

    @Override
    public Page<User> findAll(int page) {
        log.info("Gateway FindAll");
        return repository.findAll(new PageRequest(page, 10));
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
