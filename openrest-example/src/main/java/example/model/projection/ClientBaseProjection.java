package example.model.projection;

import orest.expression.registry.Expand;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import example.model.Client;

@Projection(types = Client.class, name = "clientBase")
@Expand("county")
public interface ClientBaseProjection {

	String getName();
	
	@Value("#{target.county.name}")
	String getCountyName();
	
}
