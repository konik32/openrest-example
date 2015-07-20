package example.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Table(name = "clients")
@Entity
@Getter
@Setter
public class Client extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3893508830007776759L;

	private String name;

	private String phoneNr;

	@Embedded
	private Address address;
	@Embedded
	private CompanyData companyData;

	@ManyToOne
	private Department department;

	@ManyToMany()
	@JoinTable(name = "client_products", joinColumns = { @JoinColumn(name = "client_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id") }, uniqueConstraints = { @UniqueConstraint(name = "client_product_uq", columnNames = {
			"client_id", "product_id" }) })
	private Set<Product> products;

	public void addProduct(Product product) {
		if (products == null) {
			products = new HashSet<Product>();
		}
		products.add(product);
	}

}
