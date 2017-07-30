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
public class UserDataContractConverter implements Converter<User, UserDataContract> {

    private final ObjectMapper mapper;

    @Override
    public UserDataContract convert(final User user) {
        log.info("Converter User: {}", user);
        return mapper.convertValue(user, UserDataContract.class);
    }
}
