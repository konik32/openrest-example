package example.repositories;

import orest.repository.PredicateContextQueryDslRepository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import example.model.Product;


@RepositoryRestResource
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>,
		PredicateContextQueryDslRepository<Product> {

}
