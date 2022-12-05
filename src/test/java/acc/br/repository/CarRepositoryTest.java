package acc.br.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import acc.br.model.Car;
import acc.br.repository.CarRepository;
import acc.br.resource.CarV2Resource;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class CarRepositoryTest {
	 @Inject CarRepository carRepository;
	 @Inject CarV2Resource carV2Resource;
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
	 
	 @Test
	 public void countCarTest() {
		 Long countCar = carRepository.countCar();
		 Assertions.assertNotNull(countCar);
	 }
	 
	 @Test
	 public void countCarsAvailableSaleTest() {
		 Long countCarsAvailableSale = carRepository.countCarsAvailableSale();
		 Assertions.assertNotNull(countCarsAvailableSale);
	 }
	 
	 @Test
	 public void saveTest() {
		 carRepository.save(car);
		 Assertions.assertNotNull(car);
	 }
	 
	 @Test
	 public void listAllCarToSaleTest() {
		 List<Car> listAllCarToSale = carRepository.listAllCarToSale();
		 Assertions.assertNotNull(listAllCarToSale);
	 }
	 
	 @Test
	 public void listCarSortNameAndBrandTest() {
		 List<Car> listCarSortNameAndBrand = carRepository.listCarSortNameAndBrand();
//		 Assertions.assertFalse(listAll.isEmpty());
		 Assertions.assertNotNull(listCarSortNameAndBrand);
	 }	 
	 
	 @Test
	 public void listCarsByYearTest() {
		 List<Car> listCarsByYear = carRepository.listCarsByYear(LocalDate.now().getYear());
//		 Assertions.assertFalse(listAll.isEmpty());
		 Assertions.assertNotNull(listCarsByYear);
	 }
	 
	 @Test
	 public void findByNameTest() {
		 Car findByName = carRepository.findByName("carro do teste drive");
//		 Assertions.assertFalse(listAll.isEmpty());
		 Assertions.assertNotNull(findByName);
	 }
	 
	 @Test
	 public void listCarByPageTest() {
		 int page = 0;
		 int size = 1;
		 List<Car> listCarByPage = carRepository.listCarByPage(page, size);
//		 Assertions.assertFalse(listAll.isEmpty());
		 Assertions.assertNotNull(listCarByPage);
	 }
	 
//	 @Test
//	 public void removeTest() {
//		 Long id = 1L;
//		 Mockito.when(Car.findById(id)).thenReturn(id);
////		 Car carEntity = Car.findById(id);
//		 carRepository.delete(id);
////		 Assertions.assertNull(carEntity);
//	 }
	 
//	 @Test
//	 public void listAllTest() {
//		 List<Car> listAll = carRepository.listAll();
////		 Assertions.assertFalse(listAll.isEmpty());
//		 Assertions.assertNotNull(listAll);
//	 }
} 
