package example.model.dto;

import javax.validation.Valid;

import lombok.Data;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;
import example.model.Department;


@Data
@Dto(entityType = Department.class, name = "departmentDto", type = DtoType.CREATE)
public class DeparmentDto {

	private String name;
	@Valid
	private AddressDto address;
	private Boolean active;
}
