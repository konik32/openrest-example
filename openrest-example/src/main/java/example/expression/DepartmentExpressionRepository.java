package example.expression;

import pl.openrest.filters.predicate.annotation.Predicate;
import pl.openrest.filters.predicate.annotation.Predicate.PredicateType;
import pl.openrest.filters.predicate.annotation.PredicateRepository;
import pl.openrest.filters.query.annotation.StaticFilter;

import com.mysema.query.types.expr.BooleanExpression;

import example.model.Department;
import example.model.QDepartment;

@PredicateRepository(value = Department.class, defaultedPageable = false)
public class DepartmentExpressionRepository {

    @StaticFilter
    @Predicate
    public BooleanExpression active() {
        return QDepartment.department.active.eq(true);
    }

    @Predicate(type = PredicateType.SEARCH)
    public BooleanExpression nameEq(String name) {
        return QDepartment.department.name.eq(name);
    }
}
