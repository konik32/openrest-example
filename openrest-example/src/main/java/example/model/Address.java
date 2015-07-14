package example.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
@Data
public class Address {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7682019935207959596L;

	@Size(min = 3, max = 64)
	@Column(nullable = false, length = 64)
	@NotEmpty
	private String city;

	@Size(min = 3, max = 64)
	@Column(nullable = false, length = 64)
	@NotEmpty
	private String street;

	@Pattern(regexp = "^[0-9]{2}-[0-9]{3}$")
	@NotEmpty
	@Column(length = 6)
	private String zip;

	@Size(min = 1, max = 20)
	@NotEmpty
	@Column(nullable = false, name = "home_nr", length = 20)
	private String homeNr;

}
