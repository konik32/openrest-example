package example.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class CompanyData {

	private String nip;
	private String regon;
	private String krs;

}
