import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {

    public void inject (Object object) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz = object.getClass();
        Field [] fields = object.getClass().getDeclaredFields();
        for (Field field : fields){
            if (field.getAnnotation(AutoInjectable.class)!=null){
                String interfaceName = field.getType().getName();
                String className = readProperties(interfaceName);
                field.setAccessible(true);
                Class myClass = Class.forName(className);
                field.set(object, myClass.newInstance());
                field.setAccessible(false);
            }
        }
    }

    public String readProperties(String myInterface) throws IOException {
        File file = new File ("data.properties");
        String value="";
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        value = properties.getProperty(myInterface);
        return value;
    }
}
