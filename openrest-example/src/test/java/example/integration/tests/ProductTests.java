package example.integration.tests;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import example.Application;
import example.model.Product;
import example.repositories.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ProductTests {

	@Autowired
	private ProductRepository productRepository;

	@Before
	public void setUp() {
		productRepository.deleteAll();

	}

	@Test
	public void shouldFilterProducts() {
		for (int i = 0; i < 10; i++) {
			Product product = new Product();
			product.setPrice(i * 10);
			product.setProductionYear(i * 10 + 1920);
			product.setName("IPHONE " + i);
			productRepository.save(product);
		}

		given().param("orest", "").param("filters", "priceBetween(0;25)").get("/api/products").then()
				.body("page.totalElements", equalTo(3));

		given().param("orest", "").param("filters", "productionYearGt(1920)").get("/api/products").then()
				.body("page.totalElements", equalTo(9));

		given().param("orest", "")
				.param("filters", "nameLike(IPHONE);or;productionYearGt(2000);and;priceBetween(0;25)")
				.get("/api/products").then().body("page.totalElements", equalTo(10));
	}
}
