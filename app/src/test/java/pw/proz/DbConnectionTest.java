package pw.proz;

import org.junit.jupiter.api.*;
import pw.proz.database.dbutils.DbManager;
import pw.proz.utils.exceptions.DbExceptions;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Database connection tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DbConnectionTest {

    @Test
    @Order(2)
    @DisplayName("Should throw wrong username or password exception")
    void initConnectionExceptionTesting() {
        Exception exception = assertThrows(DbExceptions.class, () ->
                DbManager.initDatabase("wrongUserName","wrongPassword"));
        assertEquals("Wrong user name or password [28000-200]", exception.getMessage());
    }
    @Test
    @Order(1)
    @DisplayName("Should initialize database quickly")
    void timeoutNotExceeded() {
        assertTimeout(ofSeconds(5), () -> {
            DbManager.initDatabase();
        });
    }


}
