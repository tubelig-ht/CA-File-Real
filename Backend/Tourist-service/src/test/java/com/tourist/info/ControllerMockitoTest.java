package com.tourist.info;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.tourist.info.controller.TouristController;
import com.tourist.info.model.Tourist;
import com.tourist.info.service.TouristService;

@SpringBootTest
public class ControllerMockitoTest {
	
	@Autowired
	private TouristController touristController;
	
	@MockBean
	private TouristService touristService;
	
	@Test
	public void testAddTourist(){
		Tourist tourist=new Tourist(101,"demo1","demo1","Male",20,"Pune",3);
		Mockito.when(touristService.addTourist(new Tourist())).thenReturn(tourist);
		ResponseEntity<Tourist> response=touristController.addTourist(tourist);
		assertEquals(HttpStatus.CREATED,response.getStatusCode());
	}
	
	@Test
	public void testGetAllTourists() {
		List<Tourist> touristList=List.of(
				new Tourist(101,"demo1","demo1","Male",20,"Pune",3),
				new Tourist(101,"demo2","demo2","Female",25,"Banglore",6));
		Mockito.when(touristService.getAllTourists()).thenReturn(touristList);
		ResponseEntity<List<Tourist>> response=touristController.getAllTourists();
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(2,response.getBody().size());
	}
	
}
