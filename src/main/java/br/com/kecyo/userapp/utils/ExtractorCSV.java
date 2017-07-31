package br.com.kecyo.userapp.utils;

import br.com.kecyo.userapp.config.migration.MigrationMongoDB;
import br.com.kecyo.userapp.entities.User;
import com.google.common.collect.Sets;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ExtractorCSV {

    private Function<String, User> mapToItem = (line) -> {
        final String[] p = line.split(";");
        return User.builder()
                .id(p[0])
                .name("user - ".concat(p[0].trim()))
                .devices(Sets.newHashSet(p[1].trim()))
                .build();
    };

    public Map<String, Optional<User>> execute() throws URISyntaxException, IOException {

        Map<String, Optional<User>> collect;

        final InputStream inputFS = this.getClass().getResourceAsStream("/load/loadUsers.csv");
        final BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));

        collect = br.lines()
                .map(mapToItem)
                .collect(Collectors.groupingBy(User::getId,
                        Collectors.reducing((user, user2) -> {
                            user.getDevices().addAll(user2.getDevices());
                            return user;
                        }))
                );

        br.close();

        return collect;
    }


}
