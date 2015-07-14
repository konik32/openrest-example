package example.expression;

import orest.expression.registry.ExpressionMethod;
import orest.expression.registry.ExpressionRepository;
import orest.expression.registry.Join;

import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.types.expr.BooleanExpression;

import example.model.Client;
import example.model.QClient;
import example.model.QCounty;
import example.model.QProduct;

@ExpressionRepository(Client.class)
public class ClientExpressionRepository {

	@ExpressionMethod(searchMethod = true, joins = @Join(value = "products"))
	public BooleanExpression productIdEq(Long productId) {
		return QProduct.product.id.eq(productId);
	}

	@ExpressionMethod(searchMethod = true, defaultedPageable=false)
	public BooleanExpression countyIdEq(Long countyId) {
		JPASubQuery subQuery = new JPASubQuery();
		subQuery.from(QCounty.county);
		subQuery.where(QCounty.county.id.eq(countyId));
		return QClient.client.county.id.in(subQuery.list(QCounty.county.id));
	}

}
