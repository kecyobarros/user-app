package br.com.kecyo.userapp.config.mongobee;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoBeeConfig {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Bean
    public Mongobee mongobee(final MongoClient mongoClient){
        Mongobee runner = new Mongobee(mongoClient);
        runner.setDbName(database);
        runner.setChangeLogsScanPackage(
                "br.com.kecyo.userapp.config.migration");
        runner.setEnabled(true);
        return runner;
    }
}
