package com.nibi.springdata.product;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.nibi.springdata.product.entities.Product;
import com.nibi.springdata.product.repos.ProductRepository;






@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductdataApplicationTests {

	@Autowired
	ProductRepository repository;
	
	
	//
	@Test
	public void contextLoads() {
	}
	
	
	//
	@Test
	public void testCreate() {
		Product product = new Product();
		
		product.setId(1);
		product.setName("Iphone");
		product.setDesc("Nice");
		product.setPrice(250d);
		
		repository.save(product);
	}
	
	
	//
	@Test
	public void testRead() {
		
		Product product = repository.findById(1).get();
	
		assertNotNull(product);
		assertEquals("Iphone",  product.getName());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + product.getDesc());
	
	}

	
	//
	@Test
	public void testUpdate() {
		
		Product product = repository.findById(1).get();
		product.setPrice(1200d);
		repository.save(product);
		
	}
	
	
/*	//
	@Test
	public void testDelete() {
		
		if(repository.existsById(1)) {
			System.out.println("Deleting a product");
			repository.deleteById(1);
		}
	}
*/	
	
	
	//
	@Test
	public void testCount() {
		System.out.println("Total recount ------>>>" + repository.count());
	}
	
	
	
	//
	@Test
	public void testFindByName() {
		List<Product> products = repository.findByName("Iwatch");
		products.forEach(p->System.out.println(p.getPrice()));
	}
	
	
	
	//
	@Test
	public void testFindByNameAndDesc() {
		List<Product> products = repository.findByNameAndDesc("TV", "From Samsung Inc");
		products.forEach(p->System.out.println(p.getPrice()));
	}
	
	
	
	
	//
	@Test
	public void testFindByPriceGreaterThan() {
		List<Product> products = repository.findByPriceGreaterThan(100d);
		products.forEach(p->System.out.println(p.getPrice() + " - " + p.getName() ));
	}
	
	
	
	
	//
	@Test
	public void testFindByDescContains() {
		List<Product> products = repository.findByDescContains("Apple");
		products.forEach(p->System.out.println(p.getName() ));
	}
	
	
	
	
	//
	@Test
	public void testFindByPriceBetween() {
		List<Product> products = repository.findByPriceBetween(50d, 1500d);
		products.forEach(p->System.out.println(p.getName() + " - " + p.getPrice() ));
	}
	
	
	
	//
	@Test
	public void testFindByDescLike() {
		List<Product> products = repository.findByDescLike("%LG%");
		products.forEach(p->System.out.println(p.getName() + " - " + p.getPrice() ));
	}
	
	
	
	
	//
	@Test
	public void testFindByIdsIn() {
		
		Pageable pageable = PageRequest.of(1, 2);
		
		List<Product> products = repository.findByIdIn(Arrays.asList(1,2,3,4), pageable);
		products.forEach(p->System.out.println(p.getName() + " - " + p.getPrice() ));
	}
	
	
	
	
	
	//
	@Test
	public void testFindAllPaging() {
		
		Pageable pageable = PageRequest.of(1, 2);
		Page<Product> results = repository.findAll(pageable);
		
		results.forEach(p -> System.out.println(p.getName()));
		
	}
	
	
	
	
	//
	@Test
	public void testFindAllSorting() {
		
//		repository.findAll(new Sort(Direction.DESC, "name", "price")).forEach(p -> System.out.println(p.getName() + " - " + p.getPrice()));
		repository.findAll(new Sort(new Sort.Order(Direction.DESC, "name"), new Sort.Order("price")));
		
	}

	
	
	@Test
	public void testFindAllPagingAndSorting() {
		
	//	Pageable pageable = PageRequest(0, 2, Direction.DESC, "name");
		Pageable pageable = PageRequest.of(0, 2, Direction.DESC, "name");
		repository.findAll(pageable).forEach(p -> System.out.println(p.getName()));;
		
	}
	
	
	
}
 