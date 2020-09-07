package com.mine.firstproject.springbootfirstproject.jobs;

import javax.sql.DataSource;

import com.mine.firstproject.springbootfirstproject.User;
import com.mine.firstproject.springbootfirstproject.User2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.avro.builder.AvroItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
 

@Configuration
@EnableBatchProcessing
public class BatchConfig {
     
    @Autowired
    private JobBuilderFactory jobs;
 
    @Autowired
    private StepBuilderFactory steps;
     
    @Bean
    public Step stepOne(){
        return steps.get("stepOne")
                .tasklet(new MyTaskOne())
                .build();
    }
     
    @Bean
    public Step stepTwo(){
        return steps.get("stepTwo")
                .tasklet(new MyTaskTwo())
                .build();
    }   
     
    @Bean
    public Job demoJob(){
        return jobs.get("demoJob")
                .incrementer(new RunIdIncrementer())
                .start(stepOne())
                .next(chunkStep1())
                .next(chunkStep2())
                .next(stepTwo())
                .build();
    }

    @Autowired
    DataSource dataSource;

    public ItemReader<Object> itemReader(String name, String sql, Class className) {
        return new JdbcCursorItemReaderBuilder<User>()
                .name(name)
                .dataSource(dataSource)
                .sql(sql)
                .rowMapper(new BeanPropertyRowMapper(className))
                .build();
    }

    public ItemWriter<Object> itemWriter(String name, String schema, String outputResource, Class className) {
        return new AvroItemWriterBuilder()
                .name(name)
                .schema(new FileSystemResource(schema))
                .resource(new FileSystemResource(outputResource))
                .type(className)
                .build();
    }
   
    

    //Chunk
    public Step chunkStep1() {
        System.out.println("chunkStep1:::::::::::");
        return steps.get("chunkStep1")
            .<Object, Object>chunk(20)
            .reader(itemReader("cursorItemReader1", "select ID, NAME from TEST_1", User.class))
            .writer(itemWriter("avroItemWriter1", "src/main/resources/avro/users.avsc", "output/users.all.avro", User.class))
            .build();
    }

    public Step chunkStep2() {
        System.out.println("chunkStep2:::::::::::");
        return steps.get("chunkStep2")
            .<Object, Object>chunk(20)
            .reader(itemReader("cursorItemReader2", "select ID, NAME, DESC from TEST_2", User2.class))
            .writer(itemWriter("avroItemWriter2", "src/main/resources/avro/users2.avsc", "output/users2.all.avro", User2.class))
            .build();
    }
}