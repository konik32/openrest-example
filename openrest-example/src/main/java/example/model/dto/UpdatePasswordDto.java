package example.model.dto;

import lombok.Data;
import pl.openrest.dto.annotations.Dto;
import pl.openrest.dto.registry.DtoType;
import pl.openrest.dto.validation.annotation.ValidateExpression;
import example.model.User;


@Dto(entityType=User.class, type=DtoType.MERGE, name="updatePasswordDto")
@Data
public class UpdatePasswordDto {

	
	private String password;
	
	@ValidateExpression("#{@validators.validatePassword(dto.oldPassword)}")
	private String oldPassword;
	@ValidateExpression("#{dto.confirmPassword.equals(dto.password)}")
	private String confirmPassword;
}
