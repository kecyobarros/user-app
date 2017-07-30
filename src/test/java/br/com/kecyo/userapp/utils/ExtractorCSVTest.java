package br.com.kecyo.userapp.utils;

import br.com.kecyo.userapp.entities.User;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class ExtractorCSVTest {

    @Test
    public void testSuccess() throws IOException, URISyntaxException {
        Map<String, Optional<User>> mapUser = new ExtractorCSV().execute();

        List<User> list = mapUser.values()
                .stream()
                .map(Optional::get)
                .collect(Collectors.toList());

        assertThat(list, hasSize(2));

        User user1 = list.get(0);

        assertThat(user1, notNullValue());
        assertThat(user1.getId(), is(equalTo("813b190e-09d0-4545-80da-ade4405c8901")));
        assertThat(user1.getName(), is(equalTo("user - 813b190e-09d0-4545-80da-ade4405c8901")));
        assertThat(user1.getDevices(), hasSize(2));
        assertThat(user1.getDevices(), hasItems("ca515e36bda92767", "b08765c638cdd300"));


        User user2 = list.get(1);

        assertThat(user2, notNullValue());
        assertThat(user2.getId(), is(equalTo("b93d88c2-6636-44a1-b093-973d71fe4c5e")));
        assertThat(user2.getName(), is(equalTo("user - b93d88c2-6636-44a1-b093-973d71fe4c5e")));
        assertThat(user2.getDevices(), hasSize(2));
        assertThat(user2.getDevices(), hasItems("5c91bb852c4eaec0", "d4e3b99e0bd38c6e"));
    }
}
