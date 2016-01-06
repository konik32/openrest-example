package example.integration.tests;

import static com.jayway.restassured.RestAssured.given;

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

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import example.Application;
import example.model.Address;
import example.model.Department;
import example.model.dto.AddressDto;
import example.model.dto.DepartmentDto;
import example.repositories.DepartmentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DepartmentTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department department;

    @Before
    public void setUp() {
        departmentRepository.deleteAll();
        department = createDepartment();
        departmentRepository.save(department);
    }

    private Address createAddress() {
        Address address = new Address();
        address.setCity("Warsaw");
        address.setStreet("Krakowska");
        address.setHomeNr("123");
        address.setZip("33-290");
        return address;
    }

    private Department createDepartment() {
        Department department = new Department();
        department.setAddress(createAddress());
        department.setName("Department 1");
        department.setActive(true);
        return department;
    }

    @Test
    public void shouldReturnDepartmentsWithoutPagination() {
        given().param("orest", "").get("/api/departments").then().statusCode(HttpStatus.SC_OK)
                .body("page", Matchers.nullValue(), "_embedded.departments", Matchers.notNullValue());
    }

    @Test
    public void shouldReturnSearchedDepartmentsWithoutPagination() {
        given().param("orest", "").get("/api/departments/search/nameEq({name})", department.getName()).then().statusCode(HttpStatus.SC_OK)
                .body("page", Matchers.nullValue(), "_embedded.departments", Matchers.notNullValue());
    }

    @Test
    public void shouldAddStaticFiltersToCollection() {
        departmentRepository.deleteAll();
        for (int i = 0; i < 5; i++) {
            Department department = createDepartment();
            department.setActive(false);
            departmentRepository.save(department);
        }
        given().contentType(ContentType.JSON).param("orest", "").get("/api/departments").then().statusCode(HttpStatus.SC_OK)
                .body("page", Matchers.nullValue(), "_embedded", Matchers.nullValue());
        given().contentType(ContentType.JSON).param("orest", "").get("/api/departments/search/nameEq({name})", "Department 1").then().statusCode(HttpStatus.SC_OK)
                .body("page", Matchers.nullValue(), "_embedded", Matchers.nullValue());
    }

    @Test
    public void shouldAddStaticFiltersToCount() {
        departmentRepository.deleteAll();
        for (int i = 0; i < 5; i++) {
            Department department = createDepartment();
            department.setActive(false);
            departmentRepository.save(department);
        }
        given().param("orest").param("count").get("/api/departments").then().statusCode(HttpStatus.SC_OK)
                .body("count", Matchers.equalTo(0));
        given().param("orest").param("count").get("/api/departments/search/nameEq({name})", "Department 1").then()
                .statusCode(HttpStatus.SC_OK).body("count", Matchers.equalTo(0));
    }

    @Test
    public void shouldAddStaticFiltersToSingleResult() {
        departmentRepository.deleteAll();
        Department department = createDepartment();
        department.setActive(false);
        departmentRepository.save(department);
        given().param("orest", "").get("/api/departments/{id}", department.getId()).then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
