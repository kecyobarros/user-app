package br.com.kecyo.userapp.http.converter;

import br.com.kecyo.userapp.entities.User;
import br.com.kecyo.userapp.http.data.UserDataContract;
import br.com.kecyo.userapp.utils.ObjectMapperConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import static br.com.kecyo.userapp.utils.asserts.AssertUser.assertUser;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

public class UserConverterTest {

    private UserConverter converter;

    private ObjectMapper mapper = ObjectMapperConfig.getObjectMapper();

    @Before
    public void before() {
        converter = new UserConverter(mapper);
    }


    @Test
    public void testValidConverter() throws URISyntaxException, IOException {
        String json = Files.toString(new File(getUrl("json/userContract.json")), Charset.defaultCharset());

        UserDataContract userDataContract = mapper.readValue(json, UserDataContract.class);

        User user = converter.convert(userDataContract);

        assertThat(user, is(notNullValue()));
        assertUser(user);
    }

    private URI getUrl(String path) throws URISyntaxException {
        return this.getClass().getClassLoader().getResource(path).toURI();
    }

}
