package example.expression;

import orest.expression.registry.ExpressionMethod;
import orest.expression.registry.ExpressionRepository;
import orest.expression.registry.StaticFilter;

import com.mysema.query.types.expr.BooleanExpression;

import example.model.Department;
import example.model.QDepartment;

@ExpressionRepository(value = Department.class, defaultedPageable = false)
public class DepartmentExpressionRepository {

    @StaticFilter
    @ExpressionMethod
    public BooleanExpression active() {
        return QDepartment.department.active.eq(true);
    }

    @ExpressionMethod(searchMethod = true)
    public BooleanExpression nameEq(String name) {
        return QDepartment.department.name.eq(name);
    }
}
