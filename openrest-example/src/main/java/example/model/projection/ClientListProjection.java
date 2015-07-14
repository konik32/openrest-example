package example.model.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import example.model.Client;

@Projection(types = Client.class, name = "clientList")
public interface ClientListProjection {

	String getName();


}
