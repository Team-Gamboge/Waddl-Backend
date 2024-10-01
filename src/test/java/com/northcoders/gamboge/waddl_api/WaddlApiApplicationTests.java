package com.northcoders.gamboge.waddl_api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WaddlApiApplicationTests {

	@Test
	@DisplayName("Application context check")
	void contextLoads() {
	}

	@Test
	@DisplayName("Application check")
	void applicationStarts() {
		WaddlApiApplication.main(new String[] {});
	}

}
