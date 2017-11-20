package common.check;

import junit.framework.Assert;
import org.junit.Test;

public class BetweenNumberCheckTest {

    @Test
    //Число 10 должно входить в интервал от -20 до 20, должен вернуться true
    public void testCheck_10In2020_TrueReturned() throws Exception {
        Check check = CheckFactory.getBetweenNumberCheck("10", -20, 20);

        Boolean result = check.check();

        Assert.assertTrue(result);
    }

    @Test(expected = NumberFormatException.class)
    //при попытке задать null должен вернуться false
    public void testCheck_nullIn2020_FalseReturned() throws Exception {
        Check check = CheckFactory.getBetweenNumberCheck(null, -20, 20);

        Boolean result = check.check();

        Assert.assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    //при попытке задать abc должен вернуться false
    public void testCheck_abcIn2020_FalseReturned() throws Exception {
        Check check = CheckFactory.getBetweenNumberCheck("abc", -20, 20);

        Boolean result = check.check();

        Assert.assertFalse(result);
    }

}
