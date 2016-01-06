package example.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import pl.openrest.filters.querydsl.repository.PredicateContextQueryDslRepository;
import example.model.Client;

@RepositoryRestResource(path = "clients")
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>,
		PredicateContextQueryDslRepository<Client> {

}