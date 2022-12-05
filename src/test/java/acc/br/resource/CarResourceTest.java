package acc.br.resource;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import acc.br.model.Car;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

@QuarkusTest
public class CarResourceTest {
	
//	@InjectMock CarRepository carRepository; //declaração do métodos
//	@Inject CarResource carResource; //implementação
	private Car car;
	private Car car2;
	
	  @BeforeEach
	  void setUp() {
	    car = new Car();
//	    car.setId(1L);
	    car.setBrand("xpto");
		car.setName("carro do teste drive");
		car.setModelYear(LocalDate.now());
		car.setCreatedDateTime(LocalDateTime.now());
		car.setPrice(new BigDecimal("56"));
		car.setAvailableSale(true);
	  }
	 	  
	 	  
	@Test
	@DisplayName("should create a car")
	public void createTest() {
		
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
					.get("/cars/countCarsAvailableSale")
				.then()
					.extract().response();;
		
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertNotNull(response.jsonPath());
	}
	
	@Test
	@DisplayName("should list cars by page")
	public void listCarByPageTest() {	
		
		List<Car> cars = new ArrayList();
		cars.add(car);
		
		int page = 0;
		int size = 2;
		var response = given()
				.when()
					.get("/cars/listByPage?page="+page+ "&size="+size)
//					.get("/cars/listByPage/")
				.then()
					.extract().response();;
		
		Assertions.assertEquals(200, response.getStatusCode());
		Assertions.assertNotNull(response.jsonPath());
	}
	
	@Test
	@DisplayName("should delete car by id")
	public void deleteTest() {

		Long id = 1L;
		var response = given()
				.when()
					.delete("/cars/"+id)
				.then()
					.extract().response();;
		
		Assertions.assertEquals(204, response.getStatusCode());
	}
	
//	@Test //TODO CORRIGIR
	@DisplayName("should throw exception when id equals null")
	public void deleteTest2() throws WebApplicationException {
		Long id = 9L;
		Car carEntity = Mockito.mock(Car.class);
		Mockito.when(Car.findById(Mockito.anyLong())).thenReturn(Mockito.any());
		QuarkusMock.installMockForType(carEntity, Car.class);
		WebApplicationException exception = new WebApplicationException("Car with id " + id + " does not exist.", Response.Status.NOT_FOUND);
//		given()
//				.when()
//					.delete("/cars/"+id)
//				.then()
//					.extract().response();;
//		Assertions.assertEquals(404, exception.getResponse().getStatus());
	}
	
	@Test
	@DisplayName("should update car by id")
	public void updateTest() {

		Long id = 1L;
		var response = given()
				.when()
				.contentType(ContentType.JSON)
				.body(car)
					.put("/cars/"+id)
				.then()
					.extract().response();;
		
		Assertions.assertEquals(200, response.getStatusCode());
	}

}