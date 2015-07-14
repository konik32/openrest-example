package example.model.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;

import org.hibernate.validator.constraints.NotEmpty;

import example.model.Address;
import example.model.dto.creator.AddressDtoCreator;

@Data
@Dto(entityType = Address.class, name = "addressDto", type = DtoType.BOTH, entityCreatorType=AddressDtoCreator.class)
public class AddressDto {

	@Pattern(regexp="^(.*)[ ]+(.*), ([0-9]{2}-[0-9]{3})[ ]+(.*)$")
	private String address;

}
