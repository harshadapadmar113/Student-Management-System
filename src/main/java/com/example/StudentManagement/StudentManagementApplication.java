
package com.example.StudentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
    "com.example.StudentManagement",
    "com.hp.studentmanagement"
})
@EntityScan(basePackages = "com.hp.studentmanagement.model")
@EnableJpaRepositories(basePackages = "com.hp.studentmanagement.repository")
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }
}