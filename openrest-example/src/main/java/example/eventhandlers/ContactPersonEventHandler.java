package example.eventhandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import pl.openrest.dto.event.annotation.HandleAfterCreateWithDto;
import example.model.ContactPerson;
import example.model.dto.DepartmentContactPersonDto;
import example.repositories.DepartmentRepository;

@RepositoryEventHandler(ContactPerson.class)
@Component
public class ContactPersonEventHandler {

	@Autowired
	private DepartmentRepository departmentRepository;

	@HandleAfterCreateWithDto(dto = DepartmentContactPersonDto.class)
	public void addContactPersonToCounty(ContactPerson cp, DepartmentContactPersonDto dto) {
		dto.getDepartment().addContactPerson(cp);
		departmentRepository.save(dto.getDepartment());
	}

}
