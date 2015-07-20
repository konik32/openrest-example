package example.repositories;

import orest.repository.PredicateContextQueryDslRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import example.model.Client;
import example.model.projection.ClientListProjection;

@RepositoryRestResource(path = "clients",excerptProjection=ClientListProjection.class)
public interface ClientRepository extends PagingAndSortingRepository<Client, Long>,
		PredicateContextQueryDslRepository<Client> {

}