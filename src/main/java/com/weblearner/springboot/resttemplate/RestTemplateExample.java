package com.weblearner.springboot.resttemplate;

import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.weblearner.springboot.model.Employee;

public class RestTemplateExample {

	public static void main(String args[]) {

		RestTemplate restTemplate = new RestTemplate();

		// get method

		Employee response = restTemplate.getForEntity("http://localhost:8080/weblearner/employee/A1234", Employee.class)
				.getBody();
		System.out.println("Name ->" + response.getEmpName());

		// post

		Employee newEmployee = new Employee("D1234", "Joseph", "IT");

		String postResponse = restTemplate
				.postForEntity("http://localhost:8080/weblearner/employee", newEmployee, String.class).getBody();
		System.out.println("postResponse ->" + postResponse);

		// put

		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://localhost:8080/weblearner/employee/D1234").queryParam("deptName", "Finance");

		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		String putResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, null, String.class).getBody();
		
		System.out.println("putResponse ->" + putResponse);

		// delete

		UriComponentsBuilder builderNew = UriComponentsBuilder
				.fromHttpUrl("http://localhost:8080/weblearner/employee/D1234");

		String deleteResponse = restTemplate.exchange(builderNew.toUriString(), HttpMethod.DELETE, null, String.class)
				.getBody();

		System.out.println("deleteResponse ->" + deleteResponse);

	}

}
