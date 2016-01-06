package example.model.dto;

import lombok.Data;
import pl.openrest.dto.annotations.Dto;
import pl.openrest.dto.registry.DtoType;
import example.model.ContactPerson;

@Dto(entityType = ContactPerson.class, name = "contactPersonDto", type = DtoType.BOTH)
@Data
public class ContactPersonDto {
	private String name;
	private String surname;
	private String email;
	private String phoneNr;
}
