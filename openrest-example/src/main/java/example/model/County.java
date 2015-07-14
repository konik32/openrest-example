package example.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Table(name = "counties")
@Entity
@Getter
@Setter
public class County extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3066243792831368757L;

	private String name;

	@Embedded
	private Address address;

	@OneToMany()
	private List<ContactPerson> contactPersons;
	
	private Boolean active;

	public void addContactPerson(ContactPerson contactPerson) {
		if (contactPersons == null) {
			contactPersons = new ArrayList<ContactPerson>();
		}
		contactPersons.add(contactPerson);
	}

}
