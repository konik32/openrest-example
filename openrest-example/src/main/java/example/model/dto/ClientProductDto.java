package example.model.dto;

import lombok.Data;
import pl.openrest.dto.annotations.Dto;
import pl.openrest.dto.registry.DtoType;
import example.model.Client;
import example.model.Product;

@Data
@Dto(entityType = Product.class, name = "clientProductDto", type = DtoType.MERGE)
public class ClientProductDto {

	private Client client;
}
