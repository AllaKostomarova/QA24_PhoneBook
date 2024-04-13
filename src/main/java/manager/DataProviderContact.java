package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContact {
    @DataProvider
    public Iterator<Object[]> example(){
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("suc")
                .lastName("xxx")
                .phone("123456709889")
                .email("xxx@xxx.xxx")
                .address("City")
                .description("all fields")
                .build()
        });
        list.add(new Object[]{Contact.builder()
                .name("suc2")
                .lastName("xxx2")
                .phone("1234567897777")
                .email("xxx2@xxx.xxx")
                .address("City2")
                .build()
        });
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactWrongPhone(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("xxx-wrong Phone")
                .lastName("xxx-neg")
                .phone("12345")
                .email("xxx@xxx.xxx")
                .address("City")
                .build()
        });
        list.add(new Object[]{Contact.builder()
                .name("xxx-long Phone")
                .lastName("xxx-neg")
                .phone("1234500000000000000000000")
                .email("xxx@xxx.xxx")
                .address("City")
                .build()
        });
        list.add(new Object[]{Contact.builder()
                .name("xxx-empty Phone")
                .lastName("xxx-neg")
                .phone("")
                .email("xxx@xxx.xxx")
                .address("City")
                .build()
        });
        list.add(new Object[]{Contact.builder()
                .name("xxx-wrong Phone")
                .lastName("xxx-neg")
                .phone("qqqqqqqqqqq")
                .email("xxx@xxx.xxx")
                .address("City")
                .build()
        });
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
        String line = reader.readLine();
        while (line !=null) {
            String[] all = line.split(",");
            list.add(new Object[]{Contact.builder()
                    .name(all[0])
                    .lastName(all[1])
                    .phone(all[2])
                    .email(all[3])
                    .address(all[4])
                    .description(all[5])
                    .build()});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
