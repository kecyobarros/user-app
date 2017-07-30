package br.com.kecyo.userapp.config.migration;

import br.com.kecyo.userapp.entities.User;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExtractorCSV {

    private static Function<String, User> mapToItem = (line) -> {
        final String[] p = line.split(";");
        return User.builder()
                .id(p[0])
                .name("user - ".concat(p[0].trim()))
                .devices(Sets.newHashSet(p[1].trim()))
                .build();
    };

    public static Map<String, Optional<User>> execute() throws URISyntaxException, IOException {
        log.info("Extractor CSV");

        Map<String, Optional<User>> collect;

        final File inputF = new File(MigrationMongoDB.class.getResource("/load/loadUsers.csv").toURI());
        final  InputStream inputFS = new FileInputStream(inputF);
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
