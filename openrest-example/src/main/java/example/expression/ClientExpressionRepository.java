package example.expression;

import org.springframework.security.access.prepost.PreAuthorize;

import pl.openrest.filters.predicate.annotation.Predicate;
import pl.openrest.filters.predicate.annotation.Predicate.PredicateType;
import pl.openrest.filters.predicate.annotation.PredicateRepository;
import pl.openrest.filters.query.annotation.Join;

import com.mysema.query.types.expr.BooleanExpression;

import example.model.Client;
import example.model.QClient;
import example.model.QProduct;

@PredicateRepository(Client.class)
public class ClientExpressionRepository {

	@Predicate(type=PredicateType.SEARCH, joins = @Join(value = "products"))
	public BooleanExpression productIdEq(Long productId) {
		return QProduct.product.id.eq(productId);
	}

	@Predicate(type=PredicateType.SEARCH, defaultedPageable=false)
	@PreAuthorize("hasRole('ADMIN')")
	public BooleanExpression departmentIdEq(Long departmentId) {
//		JPASubQuery subQuery = new JPASubQuery();
//		subQuery.from(QDepartment.department);
//		subQuery.where(QDepartment.department.id.eq(departmentId));
//		return QClient.client.department.id.in(subQuery.list(QDepartment.department.id));
		return QClient.client.department.id.eq(departmentId);
	}
	
	@Predicate
	public BooleanExpression cityEq(String city){
		return QClient.client.address.city.eq(city);
	}
	
	@Predicate
	public BooleanExpression nameLike(String name){
		return QClient.client.name.like("%" + name +"%");
	}
	
	@Predicate
	public BooleanExpression cityLike(String city){
		return QClient.client.address.city.like("%" + city +"%");
	}
	
	@Predicate
	public BooleanExpression streetLike(String street){
		return QClient.client.address.street.like("%" + street +"%");
	}
	
	@Predicate
	public BooleanExpression zipLike(String zip){
		return QClient.client.address.zip.like("%" + zip +"%");
	}
	
	@Predicate
	public BooleanExpression phoneNrLike(String phoneNr){
		return QClient.client.phoneNr.like("%" + phoneNr +"%");
	}
	
	
	
	

}
