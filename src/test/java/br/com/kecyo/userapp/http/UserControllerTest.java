package br.com.kecyo.userapp.http;

import br.com.kecyo.userapp.http.data.UserDataContract;
import br.com.kecyo.userapp.usescases.exception.UserNotFoundException;
import br.com.kecyo.userapp.utils.ObjectMapperConfig;
import br.com.kecyo.userapp.utils.WebMvcTestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends WebMvcTestBase {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper mapper = ObjectMapperConfig.getObjectMapper();

    @Test
    public void endpointDeviceFindAll() throws Exception {
        given(userSearch.findAll(anyInt())).willReturn(
                new PageImpl<UserDataContract>(Collections.singletonList(createUserDataContract())));

        mvc.perform(get(EndPointMapping.USER+"/{page}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.[0].name").value("user - 8482632c-2b0c-4533-96e4-5abdbfb132be"))
                .andExpect(jsonPath("$.content.[0].devices.[0]").value("1b58b3a41c5ede3e"))
                .andExpect(jsonPath("$.content.[0].devices.[1]").value("c44268d966c2e831"))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.last").value(true))
                .andExpect(jsonPath("$.totalPages").value(1))
                .andExpect(jsonPath("$.size").value(0))
                .andExpect(jsonPath("$.number").value(0))
                .andExpect(jsonPath("$.numberOfElements").value(1))
                .andExpect(jsonPath("$.first").value(true));

    }

    @Test
    public void endpointDeviceFindById() throws Exception {
        given(userSearch.findById(anyString())).willReturn(createUserDataContract());

        mvc.perform(get(EndPointMapping.USER+"/id/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("user - 8482632c-2b0c-4533-96e4-5abdbfb132be"))
                .andExpect(jsonPath("$.devices.[0]").value("1b58b3a41c5ede3e"))
                .andExpect(jsonPath("$.devices.[1]").value("c44268d966c2e831"));

    }

    @Test
    public void endpointDeviceFindByIdNotFound() throws Exception {
        given(userSearch.findById(anyString())).willThrow(new UserNotFoundException());

        mvc.perform(get(EndPointMapping.USER+"/id/{id}", "1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User Not Found!"));

    }

    @Test
    public void endpointDeviceFindByIdInternalServerError() throws Exception {
        given(userSearch.findById(anyString())).willThrow(new RuntimeException());
        mvc.perform(get(EndPointMapping.USER+"/id/{id}", "1"))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void endpointDeviceCreate() throws Exception {
        given(userSave.save(any(UserDataContract.class))).willReturn("12312312321");

        mvc.perform(post(EndPointMapping.USER).contentType(MediaType.APPLICATION_JSON)
                .content(getJson()))
                .andExpect(status().isCreated())
                .andExpect(content().string("12312312321"))
                .andExpect(redirectedUrl("http://localhost/api/users/12312312321"));
    }

    @Test
    public void endpointInvalidData() throws Exception {
        mvc.perform(post(EndPointMapping.USER).contentType(MediaType.APPLICATION_JSON)
                .content(getJsonInvalid()))
                .andExpect(status().isBadRequest());
    }

    private String getJsonInvalid() throws URISyntaxException, IOException {
        return loadFile("json/userContractInvalid.json");
    }

    private String getJson() throws URISyntaxException, IOException {
        return loadFile("json/userContract.json");
    }

    private String loadFile(final String path) throws URISyntaxException, IOException {
        return Files.toString(new File(getUrl(path)), Charset.defaultCharset());
    }

    private UserDataContract createUserDataContract() throws IOException, URISyntaxException {
        return mapper.readValue(getJson(), UserDataContract.class);
    }

    private URI getUrl(String path) throws URISyntaxException {
        return this.getClass().getClassLoader().getResource(path).toURI();
    }
}
