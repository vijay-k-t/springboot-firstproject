package com.mine.firstproject.springbootfirstproject.jobs;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.mine.firstproject.springbootfirstproject.User;
import com.mine.firstproject.springbootfirstproject.User2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.avro.builder.AvroItemWriterBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.batch.item.ItemProcessor;
 

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



/// JPA Approach

    /*
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    //@Autowired
    EntityManagerFactory emf;

    @Autowired
    PersonRepository personRepository;

    @Primary
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(false);
        //jpaVendorAdapter.setDatabasePlatform("h2");
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("com.mine.firstproject.springbootfirstproject.*");
        emf.setPersistenceUnitName("default"); 
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean
    public FlatFileItemReader<Person> reader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("sample-data.csv"));
        reader.setLinesToSkip(1);

        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("firstName", "lastName");

        BeanWrapperFieldSetMapper<Person> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Person.class);

        lineMapper.setFieldSetMapper(fieldSetMapper);
        lineMapper.setLineTokenizer(tokenizer);
        reader.setLineMapper(lineMapper);

        return reader;
    }


    @Bean
    public JpaItemWriter<Person> writer() {
        JpaItemWriter<Person> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(emf);
        return writer;
    }

    @Bean
    public ItemProcessor<Person, Person> processor() {
        return (item) -> {
            item.concatenateName();
            return item;
        };
    }

    @Bean
    public Job importUserJob(JobExecutionListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Person, Person>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
   */

}