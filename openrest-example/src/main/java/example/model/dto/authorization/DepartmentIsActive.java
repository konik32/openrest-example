package example.model.dto.authorization;

import orest.dto.authorization.DtoAuthorizationStrategy;

import org.springframework.stereotype.Component;

import example.model.ContactPerson;
import example.model.dto.DepartmentContactPersonDto;

@Component
public class DepartmentIsActive implements DtoAuthorizationStrategy<Object, DepartmentContactPersonDto, ContactPerson> {

	@Override
	public boolean isAuthorized(Object principal, DepartmentContactPersonDto dto, ContactPerson entity) {
		return dto.getDepartment().getActive();
	}

}
