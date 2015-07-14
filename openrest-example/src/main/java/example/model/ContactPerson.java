package example.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Table(name = "contactPersons")
@Entity
@Getter
@Setter
public class ContactPerson extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1106505000051433088L;
	private String name;
	private String surname;
	private String email;
	private String phoneNr;
	
//	@ManyToOne
//	private County county;
}
