package example.model.dto;

import javax.validation.constraints.Pattern;

import lombok.Data;
import pl.openrest.dto.annotations.Dto;
import pl.openrest.dto.registry.DtoType;
import example.model.Address;

@Data
@Dto(entityType = Address.class, name = "addressDto", type = DtoType.BOTH)
public class AddressDto {

    @Pattern(regexp = "^(.*)[ ]+(.*), ([0-9]{2}-[0-9]{3})[ ]+(.*)$")
    private String address;
    
}
