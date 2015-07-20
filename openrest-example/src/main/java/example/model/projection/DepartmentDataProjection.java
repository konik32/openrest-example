package example.model.projection;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import example.model.ContactPerson;
import example.model.Department;

@Projection(types = Department.class, name = "departmentData")
public interface DepartmentDataProjection {

	String getName();

	@Value("#{T(java.lang.String).format('%s %s,%s %s', target.address.street,target.address.homeNr, target.address.zip, target.address.city)}")
	String getAddress();
	
	List<ContactPerson> getContactPersons();

}
