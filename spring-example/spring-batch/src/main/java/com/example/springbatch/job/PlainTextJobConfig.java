package com.example.springbatch.job;

import com.example.springbatch.core.domain.PlainText;
import com.example.springbatch.core.repository.PlainTextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class PlainTextJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PlainTextRepository plainTextRepository;

    @Bean("plainTextJob")
    public Job plainTextJob(Step plainTextStep) {
        return jobBuilderFactory.get("plainTextJob")
                .incrementer(new RunIdIncrementer())
                .start(plainTextStep)
                .build();
    }

    @JobScope // Job이 실행되는 동안에만 Step이 실행될 수 있도록 설정
    @Bean("plainTextStep")
    public Step plainTextStep(ItemReader itemReader,
                              ItemProcessor itemProcessor,
                              ItemWriter itemWriter) { // Step은 Chunk, Tasklet 기반 두 개가 존재함
        return stepBuilderFactory.get("plainTextStep")
                .<PlainText, String>chunk(5) // <읽어올 타입, Processing 할 타입>
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }

    @StepScope
    @Bean
    public RepositoryItemReader<PlainText> plainTextReader() {
        return new RepositoryItemReaderBuilder<PlainText>()
                .name("plainTextReader")
                .repository(plainTextRepository)
                .methodName("findBy") // JpaRepository 구현한 인터페이스에서 읽어올 메소드명
                .pageSize(5) // 읽게되는 commitInterval
                .arguments(List.of())
                .sorts(Collections.singletonMap("id", Sort.Direction.DESC))
                .build();
    }

    // 데이터에 대해 단순히 processed 문자열 추가
    @StepScope
    @Bean
    public ItemProcessor<PlainText, String> plainTextProcessor() {
        return item -> "processed " + item.getText();
    }

    // 읽은 뒤 단순 출력
    @StepScope
    @Bean
    public ItemWriter<String> plainTextWriter() {
        return items -> {
            items.forEach(System.out::println);
            System.out.println("==== chunk is finished");
        };
    }

}
