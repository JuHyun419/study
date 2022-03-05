package com.example.springbatch.job.validator;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@AllArgsConstructor
public class LocalDateParameterValidator implements JobParametersValidator {

    private String parameterName;

    @Override
    public void validate(JobParameters parameters) throws JobParametersInvalidException {
        final String localDate = parameters.getString(parameterName);

        if (!StringUtils.hasText(localDate)) {
            throw new JobParametersInvalidException(parameterName + "가 빈 문자열이거나 존재하지 않습니다.");
        }

        try {
            LocalDate.parse(localDate);
        } catch (DateTimeParseException e) {
            throw new JobParametersInvalidException(parameterName + "가 날짜 형식의 문자가 아닙니다.");
        }
    }
}
