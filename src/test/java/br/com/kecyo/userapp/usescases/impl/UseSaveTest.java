package br.com.kecyo.userapp.usescases.impl;

import br.com.kecyo.userapp.entities.User;
import br.com.kecyo.userapp.gateways.UserGateway;
import br.com.kecyo.userapp.http.converter.UserConverter;
import br.com.kecyo.userapp.http.data.UserDataContract;
import br.com.kecyo.userapp.util.ObjectMapperConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import static br.com.kecyo.userapp.util.asserts.AssertUser.assertUser;
import static org.mockito.Mockito.verify;

public class UseSaveTest {

    private UserSave userSave;

    private UserGateway userGateway;

    private UserConverter userConverter;

    private ObjectMapper mapper = ObjectMapperConfig.getObjectMapper();

    @Before
    public  void before(){
        userGateway = Mockito.mock(UserGateway.class);
        userConverter = new UserConverter(mapper);
        userSave = new UserSave(userGateway, userConverter);
    }

    @Test
    public void saveSuccess() throws IOException, URISyntaxException {
        ArgumentCaptor<User> deviceArgumentCaptor = ArgumentCaptor.forClass(User.class);
        userSave.save(createUserDataContract());

        verify(userGateway).save(deviceArgumentCaptor.capture());

        User userCapture = deviceArgumentCaptor.getValue();

        assertUser(userCapture);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionDeviceRequired() throws IOException, URISyntaxException {
        userSave.save(null);
    }

    private UserDataContract createUserDataContract() throws IOException, URISyntaxException {
        String json = Files.toString(new File(getUrl("json/userContract.json")), Charset.defaultCharset());
        return mapper.readValue(json, UserDataContract.class);
    }

    private URI getUrl(String path) throws URISyntaxException {
        return this.getClass().getClassLoader().getResource(path).toURI();
    }
}
