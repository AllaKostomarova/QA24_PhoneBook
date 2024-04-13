package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"aa@aa.ru", "Test123$"});
        list.add(new Object[]{"aa@aa.ru", "Test123$"});
        list.add(new Object[]{"x@x.ru", "Test123$"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginModel(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("aa@aa.ru").withPassword("Test123$")});
        list.add(new Object[]{new User().withEmail("x@x.ru").withPassword("Test123$")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        // read from file ---> add to list
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/logindata.csv")));
        String line = reader.readLine(); //"aa@aa.ru,Test123$"
        while (line !=null) {
            String[] userData = line.split(","); // ["aa@aa.ru']["Test123$"]
            list.add(new Object[]{new User().withEmail(userData[0]).withPassword(userData[1])});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
