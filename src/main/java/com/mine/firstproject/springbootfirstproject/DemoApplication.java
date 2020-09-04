package com.mine.firstproject.springbootfirstproject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	JobSchedulerService jobschedulerService;
	
	@GetMapping("/hello")
	public String sayHello() {
		System.out.println("hello");
		ArrayList<String> arr = new ArrayList<String>();
		for(int i=0;i<100;i++) {
			arr.add("Test" + i);
		}
		return "No Data return";
	}
	
	@GetMapping("/query/{id}")
	public Test sayHello(@PathVariable("id") String id) {
		System.out.println(id);
		List<Map<String, Object>> testobj = jdbcTemplate.query("select * from TEST_1", new Object[] {},  new ColumnMapRowMapper());
		System.out.println(testobj);
		Test result = jdbcTemplate.queryForObject("select * from TEST_1 where id=?", new Object[] {Integer.parseInt(id)}, new BeanPropertyRowMapper < Test > (Test.class));
		return result;
	}

	@PutMapping("/put")
	public Test putHello(@RequestBody Test test) {
		System.out.println(test);
		Test result = jdbcTemplate.queryForObject("select * from TEST_1 where id=?", new Object[] {test.getId()}, new BeanPropertyRowMapper < Test > (Test.class));
		return result;
	}

	@GetMapping("/initiatejob")
	public boolean initiateJob() {
		System.out.println("---------------Initiating job -----------------");
		long l1 = System.currentTimeMillis();
		jobschedulerService.run();
		long l2 = System.currentTimeMillis();
		System.out.println("Time Taken (in milli seconds) : " + (l2-l1));
		System.out.println("---------------Completing job -----------------");
		return true;
	}

	@GetMapping("/readData")
	public List<User> readData() {
		//Deserialize sample avro file
		List<User> result = new ArrayList<>();
		try {
			File avroOutput = new File("output/users.all.avro");
			Schema schema = new Schema.Parser().parse(new File("src/main/resources/avro/users.avsc"));
			DatumReader<User> bdPersonDatumReader = new ReflectDatumReader<User>(schema);
			DataFileReader<User> dataFileReader = new DataFileReader<User>(avroOutput, bdPersonDatumReader);
			User p = null;
			while(dataFileReader.hasNext()){
				System.out.println(dataFileReader.getMetaKeys());
				System.out.println(dataFileReader.getSchema());
				
				p = dataFileReader.next();
				System.out.println(p);
				result.add(p);
			}
			dataFileReader.close();

		} catch(IOException e) {System.out.println("Error reading Avro");}
		return result;
	}

	@GetMapping("/writeData")
	public List<User> writeData() {
		//Deserialize sample avro file
		List<User> result = new ArrayList<>();
		try {
			File avroOutput = new File("output/users.avro");
			Schema schema = new Schema.Parser().parse(new File("src/main/resources/avro/users.avsc"));
			DatumWriter<User> bdPersonDatumWriter = new ReflectDatumWriter<>(schema);
			DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(bdPersonDatumWriter);
			User user1 = new User();
			user1.setId(1);
			user1.setUsername("Test Name1");
			dataFileWriter.create(schema, avroOutput);
			dataFileWriter.append(user1);
			dataFileWriter.close();

		} catch(IOException e) {System.out.println("Error reading Avro");}
		return result;
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
