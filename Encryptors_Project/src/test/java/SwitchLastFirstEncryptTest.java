import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SwitchLastFirstEncryptTest {
    private SwitchLastFirstEncrypt switchLastFirstEncrypt = new SwitchLastFirstEncrypt();

    @Test
    public void testReverseEncrypt() {
        UserDetails testUserDetails = new UserDetails("test", "abcd");
        UserDetails expectedResult = new UserDetails("test", "dbca");

        switchLastFirstEncrypt.encrypt(testUserDetails);
        assertEquals(testUserDetails.getUserName(), expectedResult.getUserName());
        assertEquals(testUserDetails.getPassword(), expectedResult.getPassword());
    }
}
