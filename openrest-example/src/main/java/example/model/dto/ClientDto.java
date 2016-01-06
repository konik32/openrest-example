package example.model.dto;

import javax.validation.Valid;

import lombok.Data;
import pl.openrest.dto.annotations.Dto;
import pl.openrest.dto.registry.DtoType;
import pl.openrest.dto.validation.annotation.ValidateExpression;
import example.model.Client;
import example.model.Department;

@Data
@Dto(entityType = Client.class, name = "clientDto", type = DtoType.CREATE)
public class ClientDto {

	private String name;
	
	@Valid
	private AddressDto address;

	@Valid
	@ValidateExpression(value="#{@validators.validateCompanyDataDto(dto.companyData)}", message="CompanyData NIP, REGON, KRS must be set")
	private CompanyDataDto companyData;

	private Department department;

}
