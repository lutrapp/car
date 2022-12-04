package acc.br.resource;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import acc.br.model.Car;
import acc.br.repository.CarRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;

@QuarkusTest
public class CarV2ResourceTest {
	
	@InjectMock CarRepository carRepository; //declaração do métodos
	@Inject CarResource carResource; //implementação
	private Car car;
	
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
	  
//	  @Test
//		@DisplayName("should show all cars")
//		public void listAllTest() {	
//		  List<Car> cars = new ArrayList();
//		  cars.add(car);
//		  Mockito.when(carRepository.listAll()).thenReturn(cars);
//		  List<Car> response = carResource.listAll();
//		  Assertions.assertNotNull(response);
//	  }
	 	  
	 	  
	@Test
	@DisplayName("should create a car")
	public void createTest() {
		
		var response = given()
				.contentType(ContentType.JSON)
				.body(car)
				.when()
					.post("/cars/v2")
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
					.get("/cars/v2")
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
					.get("/cars/v2/isAvaliableSale")
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
					.get("/cars/v2/count")
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
					.get("/cars/v2/listCarSortNameAndBrand")
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
					.get("/cars/v2/listCarsByYear")
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
					.get("/cars/v2/countCarsAvailableSale")
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
					.get("/cars/v2/listByPage?page="+page+ "&size="+size)
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
					.delete("/cars/v2/"+id)
				.then()
					.extract().response();;
		
		Assertions.assertEquals(204, response.getStatusCode());
	}
	
//	@Test //TODO CORRIGIR
	@DisplayName("should throw exception when id equals null")
	public void deleteTest2() throws WebApplicationException {
		Long id = 9L;
		Car carEntity = Mockito.mock(Car.class);
		Mockito.when(carEntity.findById(Mockito.anyLong())).thenReturn(null);
		WebApplicationException exception = new WebApplicationException("Car with id " + id + " does not exist.", Response.Status.NOT_FOUND);
		given()
				.when()
					.delete("/cars/v2/"+id)
				.then()
					.extract().response();;
		Assertions.assertEquals(404, exception.getResponse().getStatus());
	}
	
	@Test
	@DisplayName("should update car by id")
	public void updateTest() {

		Long id = 1L;
		var response = given()
				.when()
				.contentType(ContentType.JSON)
				.body(car)
					.put("/cars/v2/"+id)
				.then()
					.extract().response();;
		
		Assertions.assertEquals(200, response.getStatusCode());
	}

}