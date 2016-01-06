package example.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import pl.openrest.filters.querydsl.repository.PredicateContextQueryDslRepository;
import example.model.Product;


@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>,
		PredicateContextQueryDslRepository<Product> {

}
