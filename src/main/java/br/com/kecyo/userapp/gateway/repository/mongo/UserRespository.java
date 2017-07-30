package br.com.kecyo.userapp.gateway.repository.mongo;

import br.com.kecyo.userapp.entities.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public interface UserRespository extends PagingAndSortingRepository<User, String> {
}
