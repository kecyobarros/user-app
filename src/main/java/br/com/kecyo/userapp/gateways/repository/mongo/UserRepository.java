package br.com.kecyo.userapp.gateways.repository.mongo;

import br.com.kecyo.userapp.entities.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!test")
public interface UserRepository extends PagingAndSortingRepository<User, String> {
}
