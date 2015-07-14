package example.model.dto;

import lombok.Data;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;
import example.model.Client;
import example.model.Product;

@Data
@Dto(entityType = Product.class, name = "clientProductDto", type = DtoType.MERGE)
public class ClientProductDto {

	private Client client;
}
