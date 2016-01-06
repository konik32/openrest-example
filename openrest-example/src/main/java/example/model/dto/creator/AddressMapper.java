package example.model.dto.creator;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import example.model.Address;
import example.model.dto.AddressDto;

@Mapper
public interface AddressMapper {

    @Mapping(source="dto.address", target="city")
    Address create(AddressDto dto);
}
