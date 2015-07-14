package example.model.dto;

import lombok.Data;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;
import example.model.Product;

@Data
@Dto(entityType = Product.class, name = "productDto", type = DtoType.BOTH)
public class ProductDto {
	private Integer price;
	private String name;
	private Integer productionYear;
}
