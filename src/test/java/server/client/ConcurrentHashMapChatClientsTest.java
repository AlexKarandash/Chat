package server.client;

import junit.framework.Assert;
import org.junit.Test;

public class ConcurrentHashMapChatClientsTest {

    @Test
    //после очистки коллекции, размер ждолжен быть ноль
    public void testClear_AddOneClear_SizeZeroReturned() throws Exception {
        ChatClients chatClients = new ConcurrentHashMapChatClients();
        chatClients.addUser(new ChatClient("Alex","",0));

        chatClients.clear();

        Assert.assertEquals(chatClients.getCountUser(), 0);
    }

    @Test
    //после добавления юзера и его же удаления размер коллекции должен быть ноль
    public void testRemoveUser_AddOneRemoveOne_SizeZeroReturned() throws Exception {
        ChatClients chatClients = new ConcurrentHashMapChatClients();
        chatClients.addUser(new ChatClient("Alex", "", 0));

        chatClients.removeUser("Alex");

        Assert.assertEquals(chatClients.getCountUser(), 0);
    }

    @Test
    //после добавления одно элемента размер коллекции должен быть равен одному
    public void testAddUser_One_SizeOneReturned() throws Exception {
        ChatClients chatClients = new ConcurrentHashMapChatClients();
        chatClients.addUser(new ChatClient("Alex","",0));

        Assert.assertEquals(chatClients.getCountUser(), 1);
    }

    @Test
    //имя Alex должно найтись в списке
    public void testContainsUser_Alex_TrueReturned() throws Exception {
        ChatClients chatClients = new ConcurrentHashMapChatClients();
        chatClients.addUser(new ChatClient("Alex", "", 0));

        Boolean result = chatClients.containsUser("Alex");

        Assert.assertTrue(result);
    }

    @Test
    //имя abc не должно найтись в списке
    public void testContainsUser_abc_TrueReturned() throws Exception {
        ChatClients chatClients = new ConcurrentHashMapChatClients();
        chatClients.addUser(new ChatClient("Alex", "", 0));

        Assert.assertFalse(chatClients.containsUser("abc"));
    }

    @Test
    //возвращаемый объект не должен быть пусто
    public void testGetUser_Alex_TrueReturned() throws Exception {
        ChatClients chatClients = new ConcurrentHashMapChatClients();
        chatClients.addUser(new ChatClient("Alex", "", 0));

        ChatClient chatClient = chatClients.getUser("Alex");

        Assert.assertNotNull(chatClient);
    }

    @Test
    //возвращаемый объект не должен быть пусто
    public void testGetUser_abc_TrueReturned() throws Exception {
        ChatClients chatClients = new ConcurrentHashMapChatClients();
        chatClients.addUser(new ChatClient("Alex", "", 0));

        ChatClient chatClient = chatClients.getUser("abc");

        Assert.assertNull(chatClient);
    }

    @Test
    //по умолчанию размер должен быть ноль
    public void testGetCountUser_Default_SizeZeroReturned() throws Exception {
        ChatClients chatClients = new ConcurrentHashMapChatClients();

        Assert.assertEquals(chatClients.getCountUser(), 0);
    }

    @Test
    //размер самого объекта и возвращаемой коллекции должен быть один
    public void testGetUsers_EqualSizes_TrueReturned() throws Exception {
        ChatClients chatClients = new ConcurrentHashMapChatClients();

        Assert.assertEquals(chatClients.getCountUser(), chatClients.getUsers().size());
    }
}
