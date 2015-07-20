package example.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9009763829606160299L;
	
	private String username;
	private String password;

}
