package example.integration.tests;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

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
import example.model.County;
import example.repositories.ContactPersonRepository;
import example.repositories.CountyRepository;
import example.utils.UrlHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ContanctPersonTests {

	private Map<String, Object> contactDto = new HashMap<String, Object>();
	
	@Autowired
	private CountyRepository countyRepository;
	
	@Autowired
	private ContactPersonRepository contactPersonRepository;
	
	@Before
	public void setUp() {
		countyRepository.deleteAll();
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
		
		County county = new County();
		county.setAddress(address);
		county.setName("County 1");
		county.setActive(true);

		countyRepository.save(county);
	}

	@Test
	public void shouldAddCountyContactPerson() throws JsonProcessingException {
		contactDto.put("county", "/api/counties/1");
		Response response = given().queryParam("dto", "countyContactPersonDto").contentType("application/json").body(contactDto).when()
				.post("/api/contactPersons").andReturn();
		response.then().statusCode(201);
		String location = response.getHeader("Location");
		
		ContactPerson personInDb = contactPersonRepository.findOne(UrlHelper.getIdFromLocation(location));
		assertNotNull(personInDb);
		given().param("projection", "countyData").param("orest").get("/api/counties/1").then().body("contactPersons", hasSize(1));
	}

}
