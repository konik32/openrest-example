package example.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import pl.openrest.dto.annotations.Dto;
import pl.openrest.dto.registry.DtoType;
import pl.openrest.dto.security.authorization.annotation.AuthorizeDto;
import example.model.ContactPerson;
import example.model.Department;
import example.model.dto.authorization.DepartmentIsActive;

@Getter
@Setter
@Dto(entityType = ContactPerson.class, name = "departmentContactPersonDto", type = DtoType.CREATE)
@AuthorizeDto(DepartmentIsActive.class)
public class DepartmentContactPersonDto extends ContactPersonDto {

	@NotNull
	private Department department;

}
