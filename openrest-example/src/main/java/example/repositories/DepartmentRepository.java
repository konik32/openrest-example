package example.repositories;

import orest.repository.PredicateContextQueryDslRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import example.model.Department;

@RepositoryRestResource(path = "departments")
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long>,
		PredicateContextQueryDslRepository<Department> {

}
