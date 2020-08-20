import org.junit.Assert;
import org.junit.Test;


public class ReverseEncryptTest {
    private ReverseEncrypt reverseEncrypt = new ReverseEncrypt();

    @Test
    public void testReverseEncrypt() {
        UserDetails testUserDetails = new UserDetails("test", "abcd");
        UserDetails expectedResult = new UserDetails("test", "dcba");

        reverseEncrypt.encrypt(testUserDetails);
        Assert.assertEquals(testUserDetails.getUserName(), expectedResult.getUserName());
        Assert.assertEquals(testUserDetails.getPassword(), expectedResult.getPassword());
    }
}
