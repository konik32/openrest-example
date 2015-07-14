package example.integration.tests;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.response.Response;

import example.Application;
import example.model.Address;
import example.model.Client;
import example.model.CompanyData;
import example.model.County;
import example.model.Product;
import example.repositories.ClientRepository;
import example.repositories.CountyRepository;
import example.repositories.ProductRepository;
import example.utils.UrlHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ClientTests {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CountyRepository countyRepository;
	@Autowired
	private ProductRepository productRepository;

	private Address address;
	private County county;
	private CompanyData companyData;
	private Map<String, Object> addressDto;
	private Client client;
	Map<String, Object> clientDto = new HashMap<String, Object>();

	@Before
	public void setUp() {
		clientRepository.deleteAll();
		countyRepository.deleteAll();
		productRepository.deleteAll();

		address = new Address();
		address.setCity("Warsaw");
		address.setStreet("Krakowska");
		address.setHomeNr("123");
		address.setZip("33-290");

		county = new County();
		county.setAddress(address);
		county.setName("County 1");
		county.setActive(true);

		companyData = new CompanyData();
		companyData.setKrs("32423");
		companyData.setNip("32423");
		companyData.setRegon("32423");

		countyRepository.save(county);

		client = new Client();
		client.setAddress(address);
		client.setName("Client 1");
		client.setPhoneNr("213123113");
		client.setCounty(county);
		client.setCompanyData(companyData);

		addressDto = new HashMap<String, Object>();
		addressDto.put("address", "Krakowska 57, 33-300 Warszawa");

		clientDto = new HashMap<String, Object>();
		clientDto.put("name", "Client 2");
		clientDto.put("county", "/api/counties/1");
		clientDto.put("address", addressDto);
		clientDto.put("companyData", companyData);
	}

	@Test
	public void shouldReturnClientBaseProjection() {
		clientRepository.save(client);
		given().param("orest", "").param("projection", "clientBase").when().get("/api/clients/{id}", client.getId())
				.then().statusCode(HttpStatus.SC_OK).body("name", Matchers.is(client.getName()))
				.body("countyName", Matchers.is(county.getName()));
	}

	@Test
	public void shouldCreateClient() {

		Response response = given().queryParam("dto", "clientDto").body(clientDto).contentType("application/json")
				.when().post("/api/clients").andReturn();

		assertEquals(HttpStatus.SC_CREATED, response.statusCode());
		String location = response.getHeader("Location");

		Client clientInDb = clientRepository.findOne(UrlHelper.getIdFromLocation(location));
		assertEquals("Client 2", clientInDb.getName());
		assertEquals("Warszawa", clientInDb.getAddress().getCity());
		assertEquals("Krakowska", clientInDb.getAddress().getStreet());
		assertEquals("57", clientInDb.getAddress().getHomeNr());
		assertEquals("33-300", clientInDb.getAddress().getZip());
	}

	@Test
	public void shouldBeBadRequestDueToCompanyDataExpressionValidator() {
		companyData.setNip(null);
		clientDto.put("companyData", companyData);

		given().queryParam("dto", "clientDto").body(clientDto).contentType("application/json").when()//
				.post("/api/clients").then().statusCode(HttpStatus.SC_BAD_REQUEST);
	}

	@Test
	public void shouldUpdatePhoneNrToNull() {
		clientDto.put("phoneNr", null);
		Client clientInDb = clientRepository.save(client);
		assertNotNull(clientInDb.getPhoneNr());
		given()//
		.queryParam("dto", "clientUpdate").body(clientDto).contentType("application/json").when()//
				.patch("/api/clients/{id}", clientInDb.getId()).then().statusCode(HttpStatus.SC_NO_CONTENT);

		clientInDb = clientRepository.findOne(clientInDb.getId());
		assertEquals(null, clientInDb.getPhoneNr());
	}

	@Test
	public void shouldReturnCountyClientsWithoutPagination() {
		for (int i = 0; i < 5; i++) {
			given().queryParam("dto", "clientDto").body(clientDto).contentType("application/json").post("/api/clients");
		}
		given().param("orest", "").param("projection", "clientList").get("/api/clients/search/countyIdEq(1)").then()
				.body("page", Matchers.nullValue());
	}

	@Test
	public void shouldReturnClientsWithProduct() {
		Product product = new Product();
		product.setProductionYear(1920);
		product.setPrice(100);
		productRepository.save(product);

		for (int i = 0; i < 10; i++) {
			String location = given().queryParam("dto", "clientDto").body(clientDto).contentType("application/json").post("/api/clients").thenReturn().getHeader("Location");
			if (i % 2 == 0)
				given().queryParam("dto", "clientProductDto")
						.body(Collections.singletonMap("client", "/api/clients/" + UrlHelper.getIdFromLocation(location)))
						.contentType("application/json").patch("/api/products/" + product.getId());
		}

		given().queryParam("orest", "").when().get("/api/clients/search/productIdEq({id})", product.getId()).then()
				.statusCode(HttpStatus.SC_OK).body("page.totalElements", equalTo(5));
	}

}