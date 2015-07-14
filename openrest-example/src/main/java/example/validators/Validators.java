package example.validators;

import org.springframework.stereotype.Component;

import example.model.dto.CompanyDataDto;

@Component
public class Validators {

	public boolean validateCompanyDataDto(CompanyDataDto companyDataDto) {

		if (companyDataDto.getNip() == null)
			return companyDataDto.getRegon() == null && companyDataDto.getKrs() == null;
		else
			return companyDataDto.getRegon() != null && companyDataDto.getKrs() != null;
	}
}
