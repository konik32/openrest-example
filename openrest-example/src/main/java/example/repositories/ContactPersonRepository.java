package example.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import pl.openrest.filters.querydsl.repository.PredicateContextQueryDslRepository;
import example.model.ContactPerson;

@RepositoryRestResource(path = "contactPersons")
public interface ContactPersonRepository extends PagingAndSortingRepository<ContactPerson, Long>,
        PredicateContextQueryDslRepository<ContactPerson> {

}
