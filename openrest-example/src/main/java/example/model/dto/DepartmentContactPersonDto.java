package example.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;
import orest.dto.authorization.annotation.AuthStrategies;
import orest.dto.authorization.annotation.Secure;
import example.model.ContactPerson;
import example.model.Department;
import example.model.dto.authorization.DepartmentIsActive;

@Getter
@Setter
@Dto(entityType = ContactPerson.class, name = "departmentContactPersonDto", type = DtoType.CREATE)
@AuthStrategies(DepartmentIsActive.class)
public class DepartmentContactPersonDto extends ContactPersonDto {

	@NotNull
	private Department department;

}
