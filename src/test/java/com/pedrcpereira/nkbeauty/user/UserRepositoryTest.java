package com.pedrcpereira.nkbeauty.user;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.context.annotation.Import;
import com.pedrcpereira.nkbeauty.TestcontainersConfiguration;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
@Import(TestcontainersConfiguration.class)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void findByEmail_returnsUser_whenEmailExists() {
        entityManager.persist(new User("nicole@example.com", "{bcrypt}fake-hash", "Nicole"));
        entityManager.flush();
        entityManager.clear();

        var found = userRepository.findByEmail("nicole@example.com");

        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("nicole@example.com");
        assertThat(found.get().getName()).isEqualTo("Nicole");
    }

    @Test
    void findByEmail_returnsEmpty_whenEmailDoesNotExist() {
        var found = userRepository.findByEmail("nicole@example.com");

        assertThat(found).isEmpty();
    }

    @Test
    void existsByEmail_returnsTrue_whenEmailExists() {
        entityManager.persist(new User("nicole@example.com", "{bcrypt}fake-hash", "Nicole"));
        entityManager.flush();
        entityManager.clear();

        var found = userRepository.existsByEmail("nicole@example.com");

        assertThat(found).isTrue();
    }

    @Test
    void existsByEmail_returnsFalse_whenEmailDoesNotExist() {
        var found = userRepository.existsByEmail("nicole@example.com");

        assertThat(found).isFalse();
    }

    @Test
    void save_isRejectedByUniqueConstraint_whenEmailAlreadyExists() {
        entityManager.persist(new User("nicole@example.com", "{bcrypt}fake-hash", "Nicole"));
        entityManager.flush();

        var duplicate = new User("nicole@example.com", "{bcrypt}fake-hash", "Duplicate");

        assertThatThrownBy(() -> userRepository.saveAndFlush(duplicate))
                .isInstanceOf(DataIntegrityViolationException.class)
                .cause()
                .isInstanceOfSatisfying(ConstraintViolationException.class,
                        e -> assertThat(e.getConstraintName()).isEqualTo("uk_users_email"));
    }

    @Test
    void save_isRejectedByLowerCaseConstraint_whenEmailIsNotLowercase() {
        var user = new User("Nicole@Example.com", "{bcrypt}fake-hash", "Nicole");

        assertThatThrownBy(() -> userRepository.saveAndFlush(user))
                .isInstanceOf(DataIntegrityViolationException.class)
                .cause()
                .isInstanceOfSatisfying(ConstraintViolationException.class,
                        e -> assertThat(e.getConstraintName()).isEqualTo("ck_users_email_lowercase"));
    }
}
