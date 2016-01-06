package example.model.dto.authorization;

import org.springframework.stereotype.Component;

import pl.openrest.dto.security.authorization.DtoAuthorizationStrategy;
import example.model.ContactPerson;
import example.model.dto.DepartmentContactPersonDto;

@Component
public class DepartmentIsActive implements DtoAuthorizationStrategy<Object, DepartmentContactPersonDto, ContactPerson> {

    @Override
    public int isAuthorized(Object principal, DepartmentContactPersonDto dto, ContactPerson entity) {
        return dto.getDepartment().getActive() ? 1 : -1;
    }

}
