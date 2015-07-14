package example.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;
import example.model.ContactPerson;
import example.model.County;

@Getter
@Setter
@Dto(entityType = ContactPerson.class, name = "countyContactPersonDto", type = DtoType.CREATE)
public class CountyContactPersonDto extends ContactPersonDto {

	@NotNull
	private County county;

}
