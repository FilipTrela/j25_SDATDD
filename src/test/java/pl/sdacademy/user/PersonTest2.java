package pl.sdacademy.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import pl.sdacademy.exceptions.PersonUpdateFailedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest2 {
    private Person person;

    @BeforeEach
    void setUp() {
        person = Person.create("Filip", "Trela");
    }

    @ParameterizedTest
    @ArgumentsSource(PersonNegativeAgeArguments.class)
    void shouldThrowPersonUpdateFailedExceptionWhenTrySetAgeLessOrEqual0OrNull(final String email, final Integer age) {

        final Throwable throwable = assertThrows(PersonUpdateFailedException.class,
                () -> person.setPersonDetails(email, age));

        assertThat(throwable)
                .hasMessage("Age has to be positive")
                .hasNoCause();

    }
}