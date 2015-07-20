package example.model.projection;

import orest.expression.registry.Expand;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import example.model.Client;

@Projection(types = Client.class, name = "clientBase")
@Expand("department")
public interface ClientBaseProjection {

	String getName();
	
	@Value("#{target.department.name}")
	String getDepartmentName();
	
}
