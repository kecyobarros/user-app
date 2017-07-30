package br.com.kecyo.userapp.config.migration;

import br.com.kecyo.userapp.entities.User;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;

@ChangeLog
public class MigrationMongoDB {

    @ChangeSet(order = "001", id = "initLoad", author = "Kecyo")
    public void initLoad(final MongoTemplate db) throws URISyntaxException, IOException {

        final Map<String, Optional<User>> users = ExtractorCSV.execute();

        users
            .values()
            .parallelStream()
            .map(Optional::get)
            .forEach(db::insert);
    }




}
