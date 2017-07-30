package br.com.kecyo.userapp.utils.asserts;

import br.com.kecyo.userapp.http.data.UserDataContract;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class AssertUserDataContract {

    public static void assertUserDataContract(final UserDataContract userDataContract){
        assertThat(userDataContract.getName(), is(equalTo("user - 8482632c-2b0c-4533-96e4-5abdbfb132be")));
        assertDevice(userDataContract.getDevices());
    }

    private static void assertDevice(final Set<String> device){
        assertThat(device, is(notNullValue()));
        assertThat(device, hasSize(2));
        assertThat(device, hasItems("c44268d966c2e831", "1b58b3a41c5ede3e"));
    }


}
