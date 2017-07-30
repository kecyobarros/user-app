package br.com.kecyo.userapp.usescases.impl;

import br.com.kecyo.userapp.entities.User;
import br.com.kecyo.userapp.gateways.UserGateway;
import br.com.kecyo.userapp.http.converter.UserConverter;
import br.com.kecyo.userapp.http.data.UserDataContract;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSave {

    private final UserGateway userGateway;

    private final UserConverter userConverter;

    public String save(final UserDataContract userDataContract) {

        log.info("save: {}", userDataContract);

        Preconditions.checkArgument(userDataContract != null, "User is Required");

        final User user = userConverter.convert(userDataContract);

        userGateway.save(user);

        return user.getId();
    }
}
