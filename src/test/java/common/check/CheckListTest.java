package common.check;

import common.transport.TransportFactory;
import common.transport.tcp.TcpSocketTransportFactory;
import junit.framework.Assert;
import org.junit.Test;

public class CheckListTest {

    @Test
    //скомпонованная проверка должна пройти тест
    public void testCheck_6000IntegerAndBetween_TrueReturned() throws Exception {
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        Check check = CheckFactory.getPortCheck("6000",5000, 10000, false, transportFactory);

        Boolean result = check.check();

        Assert.assertTrue(result);
    }

    @Test
    //после добавления одно элемента размер массива должен быть равен одному
    public void testAdd_One_SizeOneReturned() throws Exception {
        CheckList checkList = new CheckList();

        Check check = new IpCheck("abc");
        checkList.add(check);

        Assert.assertEquals(checkList.size(), 1);
    }

    @Test
    //после добавления двух элементов размер массива должен быть равен двум
    public void testAdd_Two_SizeTwoReturned() throws Exception {
        CheckList checkList = new CheckList();

        Check check = new IpCheck("abc");
        checkList.add(check);
        check = new IpCheck("abc");
        checkList.add(check);

        Assert.assertEquals(checkList.size(), 2);
    }

    @Test
    //после добавления одного элемента и удаления его размер массива должен быть равен нулю
    public void testRemove_AddOneRemoveOne_SizeZeroReturned() throws Exception {
        CheckList checkList = new CheckList();

        Check check = new IpCheck("abc");
        checkList.add(check);
        checkList.remove(0);

        Assert.assertEquals(checkList.size(), 0);
    }

    @Test
    //по умолчанию размер должен быть ноль
    public void testSize_Default_SizeZeroReturned() throws Exception {
        CheckList checkList = new CheckList();

        Assert.assertEquals(checkList.size(), 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    //при попытке удалить несуществующий элемент должна быть ошибка
    public void testRemove_Index_ErrorReturned() throws Exception {
        CheckList checkList = new CheckList();

        checkList.remove(0);

        Assert.assertEquals(checkList.size(), 0);
    }

}
