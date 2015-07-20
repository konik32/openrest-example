package example.repositories;

import orest.repository.PredicateContextQueryDslRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import example.model.User;


@RepositoryRestResource
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
		PredicateContextQueryDslRepository<User> {

}
