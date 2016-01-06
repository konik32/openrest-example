package example.expression;

import pl.openrest.filters.predicate.annotation.Predicate;
import pl.openrest.filters.predicate.annotation.PredicateRepository;

import com.mysema.query.types.expr.BooleanExpression;

import example.model.Product;
import example.model.QProduct;

@PredicateRepository(Product.class)
public class ProductExpressionRepository {

	@Predicate
	public BooleanExpression priceBetween(Integer from, Integer to) {
		return QProduct.product.price.between(from, to);
	}

	@Predicate
	public BooleanExpression nameLike(String name) {
		return QProduct.product.name.like(name + "%");
	}

	@Predicate
	public BooleanExpression productionYearGt(Integer productionYear) {
		return QProduct.product.productionYear.gt(productionYear);
	}

}
