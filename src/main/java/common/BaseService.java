package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public abstract class BaseService<T> {

    protected Properties properties;

    protected BaseService(String path) {
        //подгрузка параметров из конфигурационного файла
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(path);
            Reader reader = new InputStreamReader(fis, "UTF-8");
            properties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
