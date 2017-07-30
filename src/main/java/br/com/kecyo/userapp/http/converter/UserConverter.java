package br.com.kecyo.userapp.http.converter;

import br.com.kecyo.userapp.entities.User;
import br.com.kecyo.userapp.http.data.UserDataContract;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserConverter implements Converter<UserDataContract, User> {

    private final ObjectMapper mapper;

    @Override
    public User convert(final UserDataContract userDataContract) {
        log.info("Converter UserDataContract: {}", userDataContract);
        return mapper.convertValue(userDataContract, User.class);
    }
}
