package pl.sdacademy.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.sdacademy.exceptions.PersonActionException;
import pl.sdacademy.user.Person;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService personService;

    @Test
    void shouldThrowExceptionWhenTryToGetPersonByWrongEmail() {
        final String wrongEmail = "ftrela@gmail.com";

        final Throwable throwable = assertThrows(PersonActionException.class,
                () -> personService.getByEmail(wrongEmail));

        assertThat(throwable)
                .hasNoCause()
                .hasMessage("Failed to get person by email");
    }

    @Test
    void shouldReturnPersonByExistingEmail() {
        final String email = "test1@gmail.com";
        when(personRepository.getPersonList()).thenReturn(Collections.singletonList(
                new Person("fn", "ln", email, 12)
        ));

        final Person foundPerson = personService.getByEmail(email);

        assertThat(foundPerson.getEmail()).isEqualTo(email);
        verify(personRepository).getPersonList();
    }

    @Test
    void shouldFindByExistingEmail() {
        final String email = "test1@gmail.com";
        when(personRepository.getPersonList())
                .thenReturn(Collections
                        .singletonList(new Person("fn", "ln", email, 12))
                );

        final Optional<Person> foundOptionalPerson = personService.findByEmail(email);

        assertThat(foundOptionalPerson)
                .isPresent()
                .get()
                .extracting(Person::getEmail)
                .isEqualTo(email);
    }

    @Test
    void shouldGetPersonByExistingEmail() {
        final String email = "test1@gmail.com";
        when(personRepository.getPersonList())
                .thenReturn(Collections
                        .singletonList(new Person("fn", "ln", email, 12))
                );

        final Person foundPerson = personService.getByEmail(email);

        assertThat(foundPerson.getEmail()).isEqualTo(email);
        verify(personRepository).getPersonList();
    }

    @Test
    void shouldNotFindByNonExistingEmail() {
        final String email = "IDoNotExist@email.com";

        when(personRepository.getPersonList()).thenReturn(
                Collections.singletonList(new Person("Jan", "Jankowski", "asd@gmail.com", 19)
                ));

        final Optional<Person> foundPerson = personService.findByEmail(email);

        assertThat(foundPerson).isNotPresent();
        verify(personRepository).getPersonList();
    }


}