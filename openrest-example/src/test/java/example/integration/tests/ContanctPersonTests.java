package example.integration.tests;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.restassured.response.Response;

import example.Application;
import example.model.Address;
import example.model.ContactPerson;
import example.model.Department;
import example.model.dto.AddressDto;
import example.model.dto.DepartmentDto;
import example.repositories.ContactPersonRepository;
import example.repositories.DepartmentRepository;
import example.utils.UrlHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ContanctPersonTests {

	private Map<String, Object> contactDto = new HashMap<String, Object>();

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private ContactPersonRepository contactPersonRepository;

	private Department department;

	
	
	@Before
	public void setUp() {
		departmentRepository.deleteAll();
		contactPersonRepository.deleteAll();

		contactDto.put("email", "kk@gm.com");
		contactDto.put("name", "John");
		contactDto.put("surname", "Doe");
		contactDto.put("phoneNr", "333333");

		Map<String, Object> addressDto = new HashMap<String, Object>();
		addressDto.put("address", "Krakowska 57, 33-300 Warszawa");

		Address address = new Address();
		address.setCity("Warsaw");
		address.setStreet("Krakowska");
		address.setHomeNr("123");
		address.setZip("33-290");

		department = new Department();
		department.setAddress(address);
		department.setName("Department 1");
		department.setActive(true);

		departmentRepository.save(department);
	}

	@Test
	public void shouldAddDepartmentContactPerson() throws JsonProcessingException {
		contactDto.put("department", "/api/departments/"+ department.getId());
		Response response = given().queryParam("dto", "departmentContactPersonDto").contentType("application/json")
				.body(contactDto).when().post("/api/contactPersons").andReturn();
		response.then().statusCode(201);
		String location = response.getHeader("Location");

		ContactPerson personInDb = contactPersonRepository.findOne(UrlHelper.getIdFromLocation(location));
		assertNotNull(personInDb);
		given().param("projection", "departmentData").param("orest").get("/api/departments/"+ department.getId()).then().statusCode(200)
				.body("contactPersons", hasSize(1));
	}
	
	
    @Test
    public void shouldInvokeDtoAuthorizationStrategy() throws Exception {
        // given
        department.setActive(false);
        departmentRepository.save(department);
        contactDto.put("department", "/api/departments/"+ department.getId());
        // when
        given().queryParam("dto", "departmentContactPersonDto").contentType("application/json")
        .body(contactDto).when().post("/api/contactPersons").then().statusCode(HttpStatus.SC_FORBIDDEN);
        // then
    }
	
	

}
