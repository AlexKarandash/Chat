package common.check;

import junit.framework.Assert;
import org.junit.Test;

public class IntegerCheckTest {

    @Test
    //60001 должно пройти проверку
    public void testCheck_60001_TrueReturned() throws Exception {
        Check check = CheckFactory.getIntegerCheck("60001");

        Boolean result = check.check();

        Assert.assertTrue(result);
    }

    @Test
    //5000 должно пройти проверку
    public void testCheck_5000_TrueReturned() throws Exception {
        Check check = CheckFactory.getIntegerCheck("5000");

        Boolean result = check.check();

        Assert.assertTrue(result);
    }

    @Test
    //50 не должно пройти проверку
    public void testCheck_50_FalseReturned() throws Exception {
        Check check = CheckFactory.getIntegerCheck("50");

        Boolean result = check.check();

        Assert.assertFalse(result);
    }

    @Test
    //5000000 не должно пройти проверку
    public void testCheck_5000000_FalseReturned() throws Exception {
        Check check = CheckFactory.getIntegerCheck("5000000");

        Boolean result = check.check();

        Assert.assertFalse(result);
    }

    @Test
    //-60000 не должно пройти проверку
    public void testCheck_Minus60000_FalseReturned() throws Exception {
        Check check = CheckFactory.getIntegerCheck("-60000");

        Boolean result = check.check();

        Assert.assertFalse(result);
    }

    @Test
    //abc не должно пройти проверку
    public void testCheck_abc_FalseReturned() throws Exception {
        Check check = CheckFactory.getIntegerCheck("abc");

        Boolean result = check.check();

        Assert.assertFalse(result);
    }

    @Test(expected = NullPointerException.class)
    //null не должно пройти проверку
    public void testCheck_null_FalseReturned() throws Exception {
        Check check = CheckFactory.getIntegerCheck(null);

        Boolean result = check.check();

        Assert.assertFalse(result);
    }

}
