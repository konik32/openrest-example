package example.model.dto;

import javax.validation.Valid;

import lombok.Data;
import pl.openrest.dto.annotations.Dto;
import pl.openrest.dto.registry.DtoType;
import pl.openrest.dto.security.authorization.annotation.AuthorizeDto;
import example.model.Department;
import example.model.dto.authorization.DepartmentIsActive;


@Data
@Dto(entityType = Department.class, name = "departmentDto", type = DtoType.CREATE)
public class DepartmentDto {

	private String name;
	@Valid
	private AddressDto address;
	private Boolean active;
}
