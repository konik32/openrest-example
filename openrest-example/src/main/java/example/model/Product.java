package example.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1620575221937069177L;
	private Integer price;
	private String name;
	private Integer productionYear;
}
