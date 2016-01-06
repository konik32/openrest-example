package example.model.dto;

import lombok.Data;
import pl.openrest.dto.annotations.Dto;
import pl.openrest.dto.registry.DtoType;
import example.model.CompanyData;

@Data
@Dto(entityType = CompanyData.class, name = "companyDataDto", type = DtoType.BOTH)
public class CompanyDataDto {

	private String nip;

	private String regon;
	private String krs;

}
