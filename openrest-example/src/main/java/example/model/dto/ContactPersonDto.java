package example.model.dto;

import lombok.Data;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;
import example.model.ContactPerson;

@Dto(entityType = ContactPerson.class, name = "contactPersonDto", type = DtoType.BOTH)
@Data
public class ContactPersonDto {
	private String name;
	private String surname;
	private String email;
	private String phoneNr;
}
