package example.model.dto;

import lombok.Data;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;
import orest.dto.validation.annotation.ExpressionValid;
import example.model.CompanyData;

@Data
@Dto(entityType = CompanyData.class, name = "companyDataDto", type = DtoType.BOTH)
public class CompanyDataDto {

	private String nip;

	private String regon;
	private String krs;

}
