package com.join.spring.cache;

import com.join.spring.cache.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {

	@Resource
	private TestService testService;

	@Test
	public void contextLoads() {

		System.out.println(testService.get("123","123"));
		System.out.println(testService.get("asdasd","123"));
		System.out.println(testService.get("123","234"));

	}

}
