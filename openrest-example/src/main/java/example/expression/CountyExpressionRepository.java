package example.expression;

import orest.expression.registry.ExpressionMethod;
import orest.expression.registry.ExpressionRepository;
import orest.expression.registry.StaticFilter;

import com.mysema.query.types.expr.BooleanExpression;

import example.model.County;
import example.model.QCounty;

@ExpressionRepository(County.class)
public class CountyExpressionRepository {

	@StaticFilter
	@ExpressionMethod(searchMethod=true)
	public BooleanExpression active() {
		return QCounty.county.active.eq(true);
	}
}
