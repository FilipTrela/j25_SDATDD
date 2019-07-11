package pl.sdacademy.database;

import org.junit.jupiter.api.*;
import pl.sdacademy.exceptions.DatabaseStoreException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseStoreTest {

    private static DatabaseConnection databaseConnection;
    private DatabaseStore databaseStore;

    @BeforeAll
    static void setUpTestCase() {
        databaseConnection = new DatabaseConnection();
    }

    @AfterAll
    static void tearDownTestCase() {
        databaseConnection.close();
    }

    @BeforeEach
    void setUp() {
        System.out.println("Test");
        databaseStore = new DatabaseStore(databaseConnection);
        databaseConnection.open();
    }

    @AfterEach
    void tearDown() {
        databaseStore.clean();
    }

    @Test
    void shouldAddSingleData() {
        final String valueToInsert = "NoSiema";
        databaseStore.addData(valueToInsert);

        final List<String> actualValues = databaseStore.getData();
        assertTrue(actualValues.contains(valueToInsert));
    }

    @Test
    void shouldAddMultiplyData() {
        final String valueToInsertA = "NoSiema";
        final String valueToInsertB = "NoSiema1";
        final String valueToInsertC = "NoSiema2";
        final String valueToInsertD = "NoSiema3";
        final String valueToInsertE = "NoSiema4";
        databaseStore.addData(valueToInsertA, valueToInsertB, valueToInsertC, valueToInsertD, valueToInsertE);

        final List<String> actualValues = databaseStore.getData();
        assertIterableEquals(Arrays.asList(
                valueToInsertA, valueToInsertB, valueToInsertC, valueToInsertD, valueToInsertE), actualValues);
    }

    @Test
    @DisplayName("Should remove multiply data when database is not empty")
    void shouldRemoveMulyiplyDataWhenDatabaseIsNotEmpty() {
        final String valueToInsertA = "NoSiema";
        final String valueToInsertB = "NoSiema1";
        final String valueToInsertC = "NoSiema2";
        final String valueToInsertD = "NoSiema3";
        final String valueToInsertE = "NoSiema4";
        databaseStore.addData(valueToInsertA, valueToInsertB, valueToInsertC, valueToInsertD, valueToInsertE);
        databaseStore.removeData(valueToInsertA, valueToInsertC, valueToInsertD, valueToInsertE);
        final List<String> databaseValues = databaseStore.getData();
        assertIterableEquals(Collections.singletonList(valueToInsertB), databaseValues);
    }

    // test dla clean - gdy w bazie jest coś
    // test removeData - usunąć nie isniejacy element
    // test removeData - bez argumentów
    // test addData - bez argumentów
    @Test
    void shouldCleanDataWhenDatabaseIsNotEmpty() {
        final String valueToInsertA = "NoSiema";
        final String valueToInsertB = "NoSiema1";
        final String valueToInsertC = "NoSiema2";
        final String valueToInsertD = "NoSiema3";
        final String valueToInsertE = "NoSiema4";
        databaseStore.addData(valueToInsertA, valueToInsertB, valueToInsertC, valueToInsertD, valueToInsertE);
        databaseStore.clean();
        final List<String> listAfterClean = databaseStore.getData();
        assertIterableEquals(Collections.EMPTY_LIST, listAfterClean);

    }

    @Test
    void shouldDoNothingWhenTryToRemoveDataNotExistingElement() {
        databaseStore.addData("Exemple1", "Exemple2", "Exemple3");

        databaseStore.removeData("Exemple4");

        final List<String> stringListAfterRemove = databaseStore.getData();

        assertAll(
                () -> assertIterableEquals(Arrays.asList("Exemple1", "Exemple2", "Exemple3"), stringListAfterRemove),
                () -> assertEquals(3, stringListAfterRemove.size()),
                () -> assertFalse(stringListAfterRemove.isEmpty())
        );
    }

    @Test
    void shouldDoNothingWhenTryToRemoveDataNotGivenArgument() {
        databaseStore.addData("Exemple1", "Exemple2", "Exemple3");

        databaseStore.removeData();

        final List<String> stringListAfterRemove = databaseStore.getData();

        assertAll(
                () -> assertIterableEquals(Arrays.asList("Exemple1", "Exemple2", "Exemple3"), stringListAfterRemove),
                () -> assertEquals(3, stringListAfterRemove.size()),
                () -> assertFalse(stringListAfterRemove.isEmpty())
        );
    }

    @Test
    void shouldDoNothingWhenTryToAddDataNotGivenArgument() {
        databaseStore.addData();
        final List<String> listAfterClean = databaseStore.getData();
        assertIterableEquals(Collections.EMPTY_LIST, listAfterClean);

    }

    @Test
    void shouldThrowDatabaseStoreExceptionWhenTryAddDataWhenConnectionToDatabaseIsClose() {
        final String valueToInsertA = "NoSiema";
        databaseConnection.close();

        assertThatThrownBy(() -> databaseStore.addData(valueToInsertA))
                .hasNoCause()
                .hasMessage("Connection is not opened. Cannot add data")
        ;
    }

    @Test
    void shouldThrowDatabaseStoreExceptionWhenTryRemoveDataWhenConnectionToDatabaseIsClose() {
        final String valueToInsertA = "NoSiema";
        databaseConnection.close();

        assertThatExceptionOfType(DatabaseStoreException.class)
                .isThrownBy(
                        () -> databaseStore.addData(valueToInsertA))
                .withMessage("Connection is not opened. Cannot remove data")
                .withNoCause();
    }

}