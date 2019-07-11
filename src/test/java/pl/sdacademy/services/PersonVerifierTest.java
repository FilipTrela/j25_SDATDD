package pl.sdacademy.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sdacademy.exceptions.VerificationException;
import pl.sdacademy.user.Person;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonVerifierTest {
    @Mock
    private AgeVerifier ageVerifier;
    @Mock
    private EmailVerifier emailVerifier;
    @Mock
    private NameVerifier nameVerifier;
    @Mock
    private SurnameVerifier surnameVerifier;
    @InjectMocks
    private PersonVerifier personVerifier;

    @Test
    void shouldNotThrownException() throws VerificationException {
        final Person person = new Person("fn", "ln", "asd@gmail.com", 15);

        when(ageVerifier.isAgeValid(person.getAge())).thenReturn(true);
        when(emailVerifier.isEmailValid(person.getEmail())).thenReturn(true);
        when(surnameVerifier.isValid(person.getLastName())).thenReturn(true);
        when(nameVerifier.isNameValid(person.getFullName())).thenReturn(true);


        personVerifier.isValid(person);

        verify(ageVerifier).isAgeValid(person.getAge());
        verify(emailVerifier).isEmailValid(person.getEmail());
        verify(surnameVerifier).isValid(person.getLastName());
        verify(nameVerifier).isNameValid(person.getFullName());

    }

    @Test
    void shouldThrownExceptionWhenAgeIsValid() {
        final Person person = new Person("fn", "ln", "asd@gmail.com", 15);

        when(ageVerifier.isAgeValid(person.getAge())).thenReturn(false);

        final Throwable throwable = assertThrows(VerificationException.class,
                () -> personVerifier.isValid(person));

        assertThat(throwable).hasMessage("Age is invalid");
        verify(ageVerifier).isAgeValid(person.getAge());
        verifyZeroInteractions(emailVerifier, nameVerifier, surnameVerifier);
    }

    @Test
    void shouldThrownExceptionWhenEmailIsValid() {
        final Person person = new Person("fn", "ln", "asd@gmail.com", 15);
        when(ageVerifier.isAgeValid(person.getAge())).thenReturn(true);
        when(emailVerifier.isEmailValid(person.getEmail())).thenReturn(false);

        final Throwable throwable = assertThrows(VerificationException.class,
                () -> personVerifier.isValid(person));

        assertThat(throwable).hasMessageContaining("Email");
        verify(emailVerifier).isEmailValid(person.getEmail());
        verify(ageVerifier).isAgeValid(person.getAge());
        verifyZeroInteractions(nameVerifier, surnameVerifier);
    }

    @Test
    void shouldThrownExceptionWhenNameIsValid() {
        final Person person = new Person("fn", "ln", "asd@gmail.com", 15);

        when(ageVerifier.isAgeValid(person.getAge())).thenReturn(true);
        when(emailVerifier.isEmailValid(person.getEmail())).thenReturn(true);
        when(nameVerifier.isNameValid(person.getFullName())).thenReturn(false);

        final Throwable throwable = assertThrows(VerificationException.class,
                () -> personVerifier.isValid(person));

        assertThat(throwable).hasMessage("Person name is invalid");
        verify(ageVerifier).isAgeValid(person.getAge());
        verify(emailVerifier).isEmailValid(person.getEmail());
        verify(nameVerifier).isNameValid(person.getFullName());
        verifyZeroInteractions(surnameVerifier);
    }

    @Test
    void shouldThrownExceptionWhenSurnameIsValid() {
        final Person person = new Person("fn", "ln", "asd@gmail.com", 15);
        when(ageVerifier.isAgeValid(person.getAge())).thenReturn(true);
        when(emailVerifier.isEmailValid(person.getEmail())).thenReturn(true);
        when(nameVerifier.isNameValid(person.getFullName())).thenReturn(true);
        when(surnameVerifier.isValid(person.getLastName())).thenReturn(false);

        final Throwable throwable = assertThrows(VerificationException.class,
                () -> personVerifier.isValid(person));

        assertThat(throwable).hasMessage("Person last name is invalid");
        verify(surnameVerifier).isValid(person.getLastName());
        verifyZeroInteractions(emailVerifier, nameVerifier, ageVerifier);
    }

}
