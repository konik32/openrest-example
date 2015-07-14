package example.expression;

import orest.expression.registry.ExpressionMethod;
import orest.expression.registry.ExpressionRepository;

import com.mysema.query.types.expr.BooleanExpression;

import example.model.Product;
import example.model.QProduct;

@ExpressionRepository(Product.class)
public class ProductExpressionRepository {

	@ExpressionMethod
	public BooleanExpression priceBetween(Integer from, Integer to) {
		return QProduct.product.price.between(from, to);
	}

	@ExpressionMethod
	public BooleanExpression nameLike(String name) {
		return QProduct.product.name.like(name + "%");
	}

	@ExpressionMethod
	public BooleanExpression productionYearGt(Integer productionYear) {
		return QProduct.product.productionYear.gt(productionYear);
	}

}
