package example.model.dto;

import lombok.Data;
import pl.openrest.dto.annotations.Dto;
import pl.openrest.dto.registry.DtoType;
import example.model.Product;

@Data
@Dto(entityType = Product.class, name = "productDto", type = DtoType.BOTH)
public class ProductDto {
	private Integer price;
	private String name;
	private Integer productionYear;
}
