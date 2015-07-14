package example.model.projection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import example.model.County;

@Projection(types = County.class, name = "countyList")
public interface CountyListProjection {

	String getName();

	@Value("#{T(java.lang.String).format('%s %s,%s %s', target.address.street,target.address.homeNr, target.address.zip, target.address.city)}")
	String getAddress();
	
	@Value("#{@departmentRepository.countByCountyId(target.id)}")
	Long getDepartmentCount();
}
