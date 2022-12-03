package acc.br;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import acc.br.model.Car;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@QuarkusTest
public class CarResourceTest {

	
	@Test
	@DisplayName("should create a car")
	public void createTest() {
		Car car = new Car();
		car.setBrand("xpto");
		car.setName("carro do teste drive");
		car.setModelYear(LocalDate.now());
		car.setCreatedDateTime(LocalDateTime.now());
		car.setPrice(new BigDecimal("56"));
		
		var response = given()
				.contentType(ContentType.JSON)
				.body(car)
				.when()
					.post("/cars")
				.then()
					.extract().response();
		
		Assertions.assertEquals(201, response.getStatusCode());
		Assertions.assertNotNull(response.jsonPath());
	}
	
	@Test
	@DisplayName("should show all cars")
	public void listAllTest() {	
		var response = given()
				.when()
					.get("/cars")
				.then()
					.extract().response();;
		
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertNotNull(response.jsonPath());
	}
	
	@Test
	@DisplayName("should show all cars to sale")
	public void listAllCarToSaleTest() {	
		var response = given()
				.when()
					.get("/cars/isAvaliableSale")
				.then()
					.extract().response();;
		
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertNotNull(response.jsonPath());
	}
	
	@Test
	@DisplayName("should count cars")
	public void countTest() {	
		var response = given()
				.when()
					.get("/cars/count")
				.then()
					.extract().response();;
		
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertNotNull(response.jsonPath());
	}
	
	@Test
	@DisplayName("should list cars by name and brand")
	public void listCarSortNameAndBrandTest() {	
		var response = given()
				.when()
					.get("/cars/listCarSortNameAndBrand")
				.then()
					.extract().response();;
		
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertNotNull(response.jsonPath());
	}
	
	@Test
	@DisplayName("should list cars by year")
	public void listCarsByYearTest() {	
		var response = given()
				.when()
					.get("/cars/listCarsByYear")
				.then()
					.extract().response();;
		
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertNotNull(response.jsonPath());
	}
	
	@Test
	@DisplayName("should count cars available sale")
	public void countCarsAvaiableSaleTest() {	
		var response = given()
				.when()
					.get("/cars/countCarsAvaiableSale")
				.then()
					.extract().response();;
		
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertNotNull(response.jsonPath());
	}
	
//	@Test
//	@DisplayName("should list cars by page")
//	public void listCarByPageTest() {	
//		Car car = new Car();
//		car.setBrand("xpto");
//		car.setName("carro do teste drive");
//		car.setModelYear(LocalDate.now());
//		car.setCreatedDateTime(LocalDateTime.now());
//		car.setPrice(new BigDecimal("56"));
//		List cars = new ArrayList();
//		cars.add(car);
//		
//		int page = 1;
//		int size = 1;
//		var response = given()
//				.when()
//					.get("/cars/countCarsAvaiableSale/"+page+ "/"+size)
//				.then()
//					.extract().response();;
//		
//		Assertions.assertEquals(200, response.getStatusCode());
//		Assertions.assertNotNull(response.jsonPath());
//	}
	
	@Test
	@DisplayName("should delete car by id")
	public void deleteTest() {

		Long id = 9L;
		var response = given()
				.when()
					.delete("/cars/"+id)
				.then()
					.extract().response();;
		
		Assertions.assertEquals(204, response.getStatusCode());
	}
	
//	@Test
//	@DisplayName("should trrow excption car by id")
//	public void deleteTest2() {
//
//		Long id = 2L;
//		Car carEntity = Car.findById(id);
//		Mockito.
//		
//		Long id = 9L;
//		var response = given()
//				.when()
//					.delete("/cars/"+id)
//				.then()
//					.extract().response();;
//		
//		Assertions.assertEquals(204, response.getStatusCode());
//	}
	

}