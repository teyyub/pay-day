package com.payday.bank;



import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 *
 * @author anar
 *
 */

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PortfolioReportTests {

	@Autowired
	private TestRestTemplate restTemplate;
	@Test
	public void exampleTest() {

	}

	@Test
	public void toDoTest() {
		String body = this.restTemplate.getForObject("/report1", String.class);
		assertThat(body).contains("Ok");
	}

//	@Autowired
//	private MockMvc mvc;
//	@Test
//	public void toDoTest() throws Exception {
//		this.mvc
//				.perform(get("/todo"))
//				.andExpect(status().isOk())
//				.andExpect(content()
//						.contentType(MediaType.APPLICATION_JSON_UTF8));
//	}

}
