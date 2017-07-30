package br.com.kecyo.userapp.usescases.impl;

import br.com.kecyo.userapp.entities.User;
import br.com.kecyo.userapp.gateways.UserGateway;
import br.com.kecyo.userapp.http.converter.UserDataContractConverter;
import br.com.kecyo.userapp.http.data.UserDataContract;
import br.com.kecyo.userapp.usescases.exception.UserNotFoundException;
import br.com.kecyo.userapp.util.ObjectMapperConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Optional;

import static br.com.kecyo.userapp.util.asserts.AssertUserDataContract.assertUserDataContract;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

public class UserSearchTest {

    private UserSearch userSearch;

    private UserGateway userGateway;

    private UserDataContractConverter dataContractConverter;

    private ObjectMapper mapper = ObjectMapperConfig.getObjectMapper();

    @Before
    public  void before(){
        userGateway = Mockito.mock(UserGateway.class);
        dataContractConverter = Mockito.mock(UserDataContractConverter.class);
        userSearch = new UserSearch(userGateway, dataContractConverter);
    }

    @Test
    public void findAllSuccess() throws IOException, URISyntaxException {

        when(userGateway.findAll(anyInt())).thenReturn(
                new PageImpl<User>(Collections.singletonList(User.builder().build())));

        when(dataContractConverter.convert(any(User.class)))
                .thenReturn(createUserDataContract());

        Page<UserDataContract> pageUser = userSearch.findAll(1);

        assertThat(pageUser.getContent(), hasSize(1));

        UserDataContract userDataContractResult = pageUser.getContent().get(0);
        assertThat(pageUser.getTotalPages(), is(equalTo(1)));
        assertThat(pageUser.getTotalElements(), is(equalTo(1L)));

        assertUserDataContract(userDataContractResult);
    }

    @Test
    public void findAllIsEmpty(){
        when(userGateway.findAll(anyInt())).thenReturn(
                new PageImpl<User>(Collections.emptyList()));

        Page<UserDataContract> pageUser = userSearch.findAll(1);
        assertThat(pageUser.getContent(), hasSize(0));
        assertThat(pageUser.getTotalPages(), is(equalTo(1)));
        assertThat(pageUser.getTotalElements(), is(equalTo(0L)));
    }

    @Test
    public void findByIdSuccess() throws IOException, URISyntaxException {

        when(userGateway.findById(anyString())).thenReturn(Optional.ofNullable(User.builder().build()));
        when(dataContractConverter.convert(any(User.class))).thenReturn(createUserDataContract());

        UserDataContract result = userSearch.findById("1");

        assertUserDataContract(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findByIdParamNotInformed() throws IOException, URISyntaxException {
        userSearch.findById(null);
    }

    @Test(expected = UserNotFoundException.class)
    public void findByIdNotFound() throws IOException, URISyntaxException {
        when(userGateway.findById(anyString())).thenReturn(Optional.empty());
        userSearch.findById("1");
    }

    private UserDataContract createUserDataContract() throws IOException, URISyntaxException {
        String json = Files.toString(new File(getUrl("json/userContract.json")), Charset.defaultCharset());
        return mapper.readValue(json, UserDataContract.class);
    }

    private URI getUrl(String path) throws URISyntaxException {
        return this.getClass().getClassLoader().getResource(path).toURI();
    }
}
