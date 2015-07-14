package example.repositories;

import orest.repository.PredicateContextQueryDslRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import example.model.ContactPerson;

@RepositoryRestResource(path = "contactPersons")
public interface ContactPersonRepository extends PagingAndSortingRepository<ContactPerson, Long>,
		PredicateContextQueryDslRepository<ContactPerson> {

}
