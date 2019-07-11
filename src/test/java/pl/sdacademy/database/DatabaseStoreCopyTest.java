package pl.sdacademy.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DatabaseStoreCopyTest {
    @Mock
    private DatabaseConnection databaseConnection;
    @InjectMocks
    private DatabaseStoreCopy databaseStoreCopy;

    @Test
    void shouldOpenDatabaseConnectionWhenAddDataToDatabaseStore() {
        final String date = "someDataToAdd";
        final Integer[] counter = {0};
        when(databaseConnection.isOpened()).thenAnswer(invocationOnMock -> {
            return counter[0]++ != 0;
        });
        doNothing().when(databaseConnection).open();

        databaseStoreCopy.addData(date);

        assertThat(databaseStoreCopy.getData()).containsExactlyInAnyOrder(date);
    }
}