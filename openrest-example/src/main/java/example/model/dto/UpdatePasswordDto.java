package example.model.dto;

import lombok.Data;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;
import orest.validation.ExpressionValid;
import example.model.User;


@Dto(entityType=User.class, type=DtoType.MERGE, name="updatePasswordDto")
@Data
public class UpdatePasswordDto {

	
	private String password;
	
	@ExpressionValid("#{@validators.validatePassword(dto.oldPassword)}")
	private String oldPassword;
	@ExpressionValid("#{dto.confirmPassword.equals(dto.password)}")
	private String confirmPassword;
}
