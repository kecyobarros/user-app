package br.com.kecyo.userapp.usescases.impl;

import br.com.kecyo.userapp.entities.User;
import br.com.kecyo.userapp.gateway.UserGateway;
import br.com.kecyo.userapp.http.converter.UserDataContractConverter;
import br.com.kecyo.userapp.http.data.UserDataContract;
import br.com.kecyo.userapp.usescases.exception.UserNotFoundException;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSearch {

    private final UserGateway userGateway;

    private final UserDataContractConverter userDataContractConverter;

    public Page<UserDataContract> findAll(int page) {
        log.info("Search FindAll");
        return userGateway
                        .findAll(page)
                        .map(this::convert);
    }

    private UserDataContract convert(final User user){
        return userDataContractConverter.convert(user);
    }

    public UserDataContract findById(final String id) {
        log.info("Search FindById: {}", id);

        Preconditions.checkArgument(!isEmpty(id), "Id is Required");

        final User user = userGateway.findById(id)
                .orElseThrow(UserNotFoundException::new);

        return convert(user);
    }

}
