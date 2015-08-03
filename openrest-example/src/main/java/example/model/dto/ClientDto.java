package example.model.dto;

import javax.validation.Valid;

import lombok.Data;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;
import orest.dto.validation.ExpressionValid;
import example.model.Client;
import example.model.Department;

@Data
@Dto(entityType = Client.class, name = "clientDto", type = DtoType.CREATE)
public class ClientDto {

	private String name;
	
	@Valid
	private AddressDto address;

	@Valid
	@ExpressionValid("#{@validators.validateCompanyDataDto(dto.companyData)}")
	private CompanyDataDto companyData;

	private Department department;

}
