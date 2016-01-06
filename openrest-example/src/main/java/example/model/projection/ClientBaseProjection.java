package example.model.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import example.model.Client;
import example.model.Department;

@Projection(types = Client.class, name = "clientBase")
// @Expand("department")
// @Secure("hasRole('ADMIN')")
public interface ClientBaseProjection {

    String getName();

    @Value("#{target.department.name}")
    String getDepartmentName();

}
