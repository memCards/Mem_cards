import org.junit.jupiter.api.Test;
import password.Password;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PasswordTest {
    @Test
    void checkPasswordLength() {
        assertEquals(Password.getPasswordHash("123").length(), 64);
        assertEquals(Password.getPasswordHash("password").length(), 64);
        assertEquals(Password.getPasswordHash("anotherLONGpassword").length(), 64);
    }

    @Test
    void checkHash() {
        assertThat("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918",
                is(equalToIgnoringCase(Password.getPasswordHash("admin"))));
        assertThat("10a73ce931cd630714b6c4d61af62c39679599152ed6fbb06aae4575c1693f14",
                is(equalToIgnoringCase(Password.getPasswordHash("my@s3cr3tP4ssw0rd"))));
    }

    @Test
    void checkDifferentPasswords() {
        assertNotEquals(Password.getPasswordHash("pasSword"), Password.getPasswordHash("password"));
        assertNotEquals(Password.getPasswordHash("s3cr3tP4ssw0rd"), Password.getPasswordHash("s3cretP4ssw0rd"));
    }
}
