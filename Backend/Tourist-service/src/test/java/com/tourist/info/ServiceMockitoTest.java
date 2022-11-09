package com.tourist.info;

import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.tourist.info.model.Tourist;
import com.tourist.info.repository.TouristRepository;
import com.tourist.info.service.TouristService;


@SpringBootTest
public class ServiceMockitoTest {
	
	@Autowired
	TouristService touristService;
	
	@MockBean
	TouristRepository touristRepository;
	
	@Test
	public void testGetAllTourists() {
		List<Tourist> touristList=List.of(
				new Tourist(101,"demo1","demo1","Male",20,"Pune",3),
				new Tourist(101,"demo2","demo2","Female",25,"Banglore",6));
		Mockito.when(touristRepository.findByOrderByFirstNameDesc()).thenReturn(touristList);
		int actual=touristService.getAllTourists().size();
		int expected=2;
		assertEquals(expected,actual);
	}
	
	@Test
	public void testAddTourist() {
		Tourist tourist=new Tourist(101,"demo1","demo1","Male",20,"Pune",3);
		Mockito.when(touristRepository.save(null)).thenReturn(tourist);
		int actual=touristService.addTourist(tourist).getId();
		int expected=101;
		assertEquals(expected,actual);
	}
}
