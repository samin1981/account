package com.example.account.job;

import com.example.account.domain.BatchFilePerson;
import com.example.account.domain.Person;
import com.example.account.repository.PersonRepository;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import java.util.List;
import java.util.Random;


@Profile("prod")
@Configuration
@EnableBatchProcessing
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private PersonRepository personRepository;

    public BatchConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, PersonRepository personRepository) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.personRepository = personRepository;
    }
    @Bean
    public Job updatePersonsJob(Step importPersonsStep, Step updatePhoneNumberTasklet) {
        return jobBuilderFactory.get("updatePersonsJob")
                .incrementer(new RunIdIncrementer())
                .start(importPersonsStep)
                .next(updatePhoneNumberTasklet)
                .build();
    }
    @Bean
    public Step updatePhoneNumberTasklet() {
        return stepBuilderFactory.get("updatePhoneNumberTasklet")
                .tasklet((stepContribution, chunkContext) -> {
                    List<Person> persons = personRepository.findAll();
                    persons.forEach(person -> person.setPhoneNumber("09123458765"));
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
    @Bean
    public Step importPersonsStep() {
        return stepBuilderFactory.get("importPersonsStep")
                .<BatchFilePerson, Person>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    @Bean
    public ItemReader<BatchFilePerson> reader() {
        return new FlatFileItemReaderBuilder<BatchFilePerson>()
                .name("readerStep")
                .resource(new ClassPathResource("persons.csv"))
                .linesToSkip(1)
                .delimited().delimiter(";")
                .names("id", "personName", "nationalCode")
                .targetType(BatchFilePerson.class)
                .build();
    }
    @Bean
    public ItemProcessor<BatchFilePerson, Person> processor() {
        return item -> {
            Random r = new Random();
            String generatedPassword = r.ints(48, 112)
                    .limit(16)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();


            return new Person(item.getId(),
                    item.getPersonName(),
                    generatedPassword, 0);

        };
    }
    @Bean
    public ItemWriter<Person> writer() {
        return personRepository::saveAll;
    }
}
