package com.hrm.mockprojecthrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        MockProjectHrmApplication.class,
        Jsr310JpaConverters.class
})
public class MockProjectHrmApplication {

 /*   @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }*/

    public static void main(String[] args) {
        SpringApplication.run(MockProjectHrmApplication.class, args);
        Long longValue = 1565668484204L;

    }
}
