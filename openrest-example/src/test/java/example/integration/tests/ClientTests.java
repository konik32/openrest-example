package example.integration.tests;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.hamcrest.Matcher;
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
import example.model.Department;
import example.model.Product;
import example.repositories.ClientRepository;
import example.repositories.DepartmentRepository;
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
    private DepartmentRepository departmentRepository;
    @Autowired
    private ProductRepository productRepository;

    private Address address;
    private Department department;
    private CompanyData companyData;
    private Map<String, Object> addressDto;
    private Client client;
    Map<String, Object> clientDto = new HashMap<String, Object>();

    @Before
    public void setUp() {
        clientRepository.deleteAll();
        departmentRepository.deleteAll();
        productRepository.deleteAll();

        department = createDepartment();

        companyData = createCompanyData();

        departmentRepository.save(department);

        client = createClient(department);

        addressDto = new HashMap<String, Object>();
        addressDto.put("address", "Krakowska 57, 33-300 Warszawa");

        clientDto = new HashMap<String, Object>();
        clientDto.put("name", "Client 2");
        clientDto.put("department", "/api/departments/1");
        clientDto.put("address", addressDto);
        clientDto.put("companyData", companyData);
    }

    private Address createAddress() {
        Address address = new Address();
        address.setCity("Warsaw");
        address.setStreet("Krakowska");
        address.setHomeNr("123");
        address.setZip("33-290");
        return address;
    }

    private Client createClient(Department department) {
        Client client = new Client();
        client.setAddress(createAddress());
        client.setName("Client 1");
        client.setPhoneNr("213123113");
        client.setDepartment(department);
        client.setCompanyData(createCompanyData());
        return client;
    }

    private Department createDepartment() {
        Department department = new Department();
        department.setAddress(createAddress());
        department.setName("Department 1");
        department.setActive(true);
        return department;
    }

    private CompanyData createCompanyData() {
        CompanyData companyData = new CompanyData();
        companyData.setKrs("32423");
        companyData.setNip("32423");
        companyData.setRegon("32423");
        return companyData;
    }

    @Test
    public void shouldReturnClientBaseProjection() {
        clientRepository.save(client);
        given().param("orest", "").param("projection", "clientBase").when().get("/api/clients/{id}", client.getId()).then()
                .statusCode(HttpStatus.SC_OK).body("name", Matchers.is(client.getName()))
                .body("departmentName", Matchers.is(department.getName()));
    }

    @Test
    public void shouldCountAllClients() {
        for (int i = 0; i < 10; i++)
            clientRepository.save(createClient(department));
        given().param("orest").param("count").when().get("/api/clients").then().statusCode(HttpStatus.SC_OK)
                .body("count", Matchers.equalTo(10));
    }

    @Test
    public void shouldCountClientsWithDepartmentId() {
        Department department = createDepartment();
        departmentRepository.save(department);
        for (int i = 0; i < 5; i++) {
            clientRepository.save(createClient(department));
        }
        for (int i = 0; i < 5; i++)
            clientRepository.save(createClient(this.department));
        given().param("orest").param("count").when().get("/api/clients/search/departmentIdEq({id})", department.getId()).then()
                .statusCode(HttpStatus.SC_OK).body("count", Matchers.equalTo(5));
    }

    @Test
    public void shouldCreateClient() {

        Response response = given().queryParam("dto", "clientDto").body(clientDto).contentType("application/json").when()
                .post("/api/clients").andReturn();

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

        Response response = given().queryParam("dto", "clientDto").body(clientDto).contentType("application/json").when()//
                .post("/api/clients");
        response.prettyPrint();
        response.then().statusCode(400);
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
    public void shouldReturnDepartmentClientsWithoutPagination() {
        for (int i = 0; i < 5; i++) {
            given().queryParam("dto", "clientDto").body(clientDto).contentType("application/json").post("/api/clients");
        }
        given().param("orest", "").param("projection", "clientList").get("/api/clients/search/departmentIdEq({id})", department.getId())
                .then().body("page", Matchers.nullValue());
    }

    @Test
    public void shouldReturnClientsWithProduct() {
        Product product = new Product();
        product.setProductionYear(1920);
        product.setPrice(100);
        productRepository.save(product);

        for (int i = 0; i < 10; i++) {
            String location = given().queryParam("dto", "clientDto").body(clientDto).contentType("application/json").post("/api/clients")
                    .thenReturn().getHeader("Location");
            if (i % 2 == 0)
                given().queryParam("dto", "clientProductDto")
                        .body(Collections.singletonMap("client", "/api/clients/" + UrlHelper.getIdFromLocation(location)))
                        .contentType("application/json").patch("/api/products/" + product.getId());
        }

        given().queryParam("orest", "").when().get("/api/clients/search/productIdEq({id})", product.getId()).then()
                .statusCode(HttpStatus.SC_OK).body("page.totalElements", equalTo(5));
    }

}
