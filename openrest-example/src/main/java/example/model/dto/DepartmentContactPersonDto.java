package example.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;
import example.model.ContactPerson;
import example.model.Department;

@Getter
@Setter
@Dto(entityType = ContactPerson.class, name = "departmentContactPersonDto", type = DtoType.CREATE)
public class DepartmentContactPersonDto extends ContactPersonDto {

	@NotNull
	private Department department;

}
