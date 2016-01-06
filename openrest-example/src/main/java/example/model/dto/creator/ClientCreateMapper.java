package example.model.dto.creator;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import example.model.Client;
import example.model.dto.ClientDto;

@Mapper
public interface ClientCreateMapper  {
public Client create(ClientDto from);
}
