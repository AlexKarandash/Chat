package common.check;

import junit.framework.Assert;
import org.junit.Test;

public class IpCheckTest {

    @Test
    //127.0.0.1 должно пройти проверку
    public void testCheck_127_0_0_1_TrueReturned() throws Exception {
        Check check = CheckFactory.getIpCheck("127.0.0.1");

        Boolean result = check.check();

        Assert.assertTrue(result);
    }

    @Test
    //abc не должно пройти проверку
    public void testCheck_abc_FalseReturned() throws Exception {
        Check check = CheckFactory.getIpCheck("abc");

        Boolean result = check.check();

        Assert.assertFalse(result);
    }

    @Test(expected = NullPointerException.class)
    //null не должно пройти проверку
    public void testCheck_null_FalseReturned() throws Exception {
        Check check = CheckFactory.getIpCheck(null);

        Boolean result = check.check();

        Assert.assertFalse(result);
    }
}
