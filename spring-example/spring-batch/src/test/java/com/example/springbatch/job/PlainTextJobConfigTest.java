package com.example.springbatch.job;

import com.example.springbatch.BatchTestConfig;
import com.example.springbatch.core.domain.PlainText;
import com.example.springbatch.core.repository.PlainTextRepository;
import com.example.springbatch.core.repository.ResultTextRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBatchTest
@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@ContextConfiguration(classes = {PlainTextJobConfig.class, BatchTestConfig.class})
class PlainTextJobConfigTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private PlainTextRepository plainTextRepository;

    @Autowired
    private ResultTextRepository resultTextRepository;

    @AfterEach
    void tearDown() {
        plainTextRepository.deleteAll();
        resultTextRepository.deleteAll();
    }

    @Test
    void success() throws Exception {
        JobExecution execution = jobLauncherTestUtils.launchJob();

        assertThat(execution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
    }

    @Test
    void success_givenNoPlainText() throws Exception {
        JobExecution execution = jobLauncherTestUtils.launchJob();

        assertThat(execution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
        assertThat(resultTextRepository.count()).isZero();
    }

    @Test
    void success_givenPlainText() throws Exception {
        // given
        givenPlainText(12);

        // when
        JobExecution execution = jobLauncherTestUtils.launchJob();

        // then
        assertThat(execution.getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
        assertThat(resultTextRepository.count()).isEqualTo(12);
    }

    private void givenPlainText(Integer count) {
        IntStream.range(0, count)
                .forEach(num -> plainTextRepository.save(new PlainText(null, "text" + num)));


    }

}
