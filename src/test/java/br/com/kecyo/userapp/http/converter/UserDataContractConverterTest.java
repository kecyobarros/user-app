package br.com.kecyo.userapp.http.converter;

import br.com.kecyo.userapp.entities.User;
import br.com.kecyo.userapp.http.data.UserDataContract;
import br.com.kecyo.userapp.utils.ObjectMapperConfig;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static br.com.kecyo.userapp.utils.asserts.AssertUserDataContract.assertUserDataContract;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class UserDataContractConverterTest {

    private UserDataContractConverter converter;

    @Before
    public void before(){
        converter = new UserDataContractConverter(ObjectMapperConfig.getObjectMapper());
    }

    @Test
    public void successConverter(){
        UserDataContract userDataContract = converter.convert(userDevice());
        assertThat(userDataContract, is(notNullValue()));
        assertThat(userDataContract.getId(), is(equalTo("8482632c-2b0c-4533-96e4-5abdbfb132be")));
        assertUserDataContract(userDataContract);
    }

    @Test
    public void testToStringBuilder(){
        assertThat(User.builder().toString(),
                is(equalTo("User.UserBuilder(id=null, name=null, devices=null, version=0)")));
    }

    private User userDevice(){
        return User.builder()
                        .id("8482632c-2b0c-4533-96e4-5abdbfb132be")
                        .name("user - 8482632c-2b0c-4533-96e4-5abdbfb132be")
                        .devices(createDevice())
                        .version(1)
                        .build();
    }

    private Set<String> createDevice(){
        return Sets.newHashSet("c44268d966c2e831", "1b58b3a41c5ede3e");


    }

}
