package example.expression;

import orest.expression.registry.ExpressionMethod;
import orest.expression.registry.ExpressionRepository;
import orest.expression.registry.StaticFilter;

import com.mysema.query.types.expr.BooleanExpression;

import example.model.QDepartment;
import example.model.User;

@ExpressionRepository(User.class)
public class UserExpressionRepository {
}
