package com.abernaty.mediscreen.repository;

import com.abernathy.mediscreen.repository.PatientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PatientRepository.class})
@EnableJpaRepositories(basePackages = {"com.abernathy.mediscreen.repository"})
@EntityScan("com.abernathy.mediscreen.model")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations="classpath:application-test.properties")
@ActiveProfiles("test")
public class PatientRepositoryTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PatientRepository patientRepository;

    @Test
    @Sql({"/insert-data.sql"})
    void injectedComponentsAreNotNull() {
        Assertions.assertThat(dataSource).isNotNull();
        Assertions.assertThat(jdbcTemplate).isNotNull();
        Assertions.assertThat(entityManager).isNotNull();
        Assertions.assertThat(patientRepository).isNotNull();
    }

    @Test
    @Sql({"/insert-data.sql"})
    void should_get_all_patients() {
        var patients = patientRepository.findAll();
        Assertions.assertThat(patients).isNotEmpty();
    }
}
