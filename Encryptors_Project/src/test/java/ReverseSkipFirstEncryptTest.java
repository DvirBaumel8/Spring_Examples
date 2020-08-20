import org.junit.Assert;
import org.junit.Test;

public class ReverseSkipFirstEncryptTest {
    private ReverseSkipFirstEncrypt reverseSkipFirstEncrypt = new ReverseSkipFirstEncrypt();

    @Test
    public void testReverseEncrypt() {
        UserDetails testUserDetails = new UserDetails("test", "abcd");
        UserDetails expectedResult = new UserDetails("test", "adcb");

        reverseSkipFirstEncrypt.encrypt(testUserDetails);
        Assert.assertEquals(testUserDetails.getUserName(), expectedResult.getUserName());
        Assert.assertEquals(testUserDetails.getPassword(), expectedResult.getPassword());
    }
}
