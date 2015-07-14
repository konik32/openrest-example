package example.model.dto;

import lombok.Data;
import orest.dto.Dto;
import orest.dto.Dto.DtoType;
import orest.dto.Nullable;
import example.model.Client;
import example.model.County;

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
