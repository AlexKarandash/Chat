package common.command;

import junit.framework.Assert;
import org.junit.Test;

public class HashMapCommandsTest {

    @Test
    //после добавления одно элемента размер массива должен быть равен одному
    public void testAdd_One_SizeOneReturned() throws Exception {
        Commands commands = new HashMapCommands();

        Command command = new CountUserCommand(null, null);
        commands.add(command);

        Assert.assertEquals(commands.size(), 1);
    }

    @Test
    //после добавления двух элементов размер массива должен быть равен двум
    public void testAdd_Two_SizeTwoReturned() throws Exception {
        Commands commands = new HashMapCommands();

        Command command = new CountUserCommand(null, null);
        commands.add(command);
        command = new ExitCommand(null, null);
        commands.add(command);

        Assert.assertEquals(commands.size(), 2);
    }

    @Test
    //после добавления одного элемента и удаления его размер массива должен быть равен нулю
    public void testRemove_AddOneRemoveOne_SizeZeroReturned() throws Exception {
        Commands commands = new HashMapCommands();

        Command command = new CountUserCommand(null, null);
        commands.add(command);
        commands.remove(command.getName());

        Assert.assertEquals(commands.size(), 0);
    }

    @Test
    //по умолчанию размер должен быть ноль
    public void testSize_Default_SizeZeroReturned() throws Exception {
        Commands commands = new HashMapCommands();

        Assert.assertEquals(commands.size(), 0);
    }

    @Test
    //размер самого объекта и возвращаемой коллекции должен быть один
    public void testGetCommands_EqualSizes_TrueReturned() throws Exception {
        Commands commands = new HashMapCommands();

        Assert.assertEquals(commands.size(), commands.getCommands().size());
    }

    @Test
    //имя CountUser должно найтись в списке
    public void testContains_CountUser_TrueReturned() throws Exception {
        Commands commands = new HashMapCommands();

        Command command = new CountUserCommand(null, null);
        commands.add(command);

        Assert.assertTrue(commands.contains(command.getName()));
    }

    @Test
    //имя abc не должно найтись в списке
    public void testContains_abc_FalseReturned() throws Exception {
        Commands commands = new HashMapCommands();

        Command command = new CountUserCommand(null, null);
        commands.add(command);

        Assert.assertFalse(commands.contains("abc"));
    }

    @Test
    //возвращаемый объект не должен быть пусто
    public void testGetByName_CountUser_TrueReturned() throws Exception {
        Commands commands = new HashMapCommands();
        Command command = new CountUserCommand(null, null);
        commands.add(command);

        command = commands.getByName(command.getName());

        Assert.assertNotNull(command);
    }

    @Test
    //возвращаемый объект должен быть пустым
    public void testGetByName_abc_TrueReturned() throws Exception {
        Commands commands = new HashMapCommands();

        Command command = new CountUserCommand(null, null);
        commands.add(command);
        command = commands.getByName("abc");

        Assert.assertNull(command);
    }
}
