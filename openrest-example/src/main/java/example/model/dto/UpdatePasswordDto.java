package example.model.dto;

import lombok.Data;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;
import orest.dto.validation.annotation.ValidateExpression;
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
