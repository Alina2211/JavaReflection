import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {
    /**
     *
     * @param object объект, чьи поля подвергаются анализу, в случае, если помечены нужной инотацией
     *               создается экземпляр нужного класса
     * @return возвращает объект с созданными экземплярами полей
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Object inject (Object object) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
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
        return object;
    }

    /**
     * Метод, считывающий файл со свойствами и отыскивающий значение свойства для искомого интерфейса
     * @param myInterface интерфейс, для которого ищется класс, реализующий его
     * @return название класса, реализующий данный интерфейс
     * @throws IOException
     */
    public String readProperties(String myInterface) throws IOException {
        File file = new File ("./src/data.properties");
        String value="";
        Properties properties = new Properties();
        properties.load(new FileReader(file));
        value = properties.getProperty(myInterface);
        return value;
    }
}
