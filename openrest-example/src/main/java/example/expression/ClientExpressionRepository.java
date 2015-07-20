package example.expression;

import org.springframework.security.access.prepost.PreAuthorize;

import orest.expression.registry.ExpressionMethod;
import orest.expression.registry.ExpressionRepository;
import orest.expression.registry.Join;

import com.mysema.query.types.expr.BooleanExpression;

import example.model.Client;
import example.model.QClient;
import example.model.QProduct;

@ExpressionRepository(Client.class)
public class ClientExpressionRepository {

	@ExpressionMethod(searchMethod = true, joins = @Join(value = "products"))
	public BooleanExpression productIdEq(Long productId) {
		return QProduct.product.id.eq(productId);
	}

	@ExpressionMethod(searchMethod = true, defaultedPageable=false)
	@PreAuthorize("hasRole('ADMIN')")
	public BooleanExpression departmentIdEq(Long departmentId) {
//		JPASubQuery subQuery = new JPASubQuery();
//		subQuery.from(QDepartment.department);
//		subQuery.where(QDepartment.department.id.eq(departmentId));
//		return QClient.client.department.id.in(subQuery.list(QDepartment.department.id));
		return QClient.client.department.id.eq(departmentId);
	}
	
	@ExpressionMethod
	public BooleanExpression cityEq(String city){
		return QClient.client.address.city.eq(city);
	}
	
	@ExpressionMethod
	public BooleanExpression nameStartsWith(String name){
		return QClient.client.name.startsWith(name);
	}
	
	

}
