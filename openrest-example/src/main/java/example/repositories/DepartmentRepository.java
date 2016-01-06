package example.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import pl.openrest.filters.querydsl.repository.PredicateContextQueryDslRepository;
import example.model.Department;

@RepositoryRestResource(path = "departments")
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long>,
		PredicateContextQueryDslRepository<Department> {

}
