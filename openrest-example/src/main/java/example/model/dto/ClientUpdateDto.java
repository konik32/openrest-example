package example.model.dto;

import lombok.Data;
import pl.openrest.dto.annotations.Dto;
import pl.openrest.dto.annotations.Nullable;
import pl.openrest.dto.registry.DtoType;
import example.model.Client;

@Data
@Dto(entityType = Client.class, name = "clientUpdate", type = DtoType.MERGE)
public class ClientUpdateDto {

	private String name;

	@Nullable("phoneNrSet")
	private String phoneNr;
	private boolean phoneNrSet = false;

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
		this.phoneNrSet = true;
	}

}
