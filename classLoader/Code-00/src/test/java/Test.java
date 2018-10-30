import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {

    @org.junit.Ignore
    public void show() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {


        CustomClassLoaderDemo loader = new CustomClassLoaderDemo();
        Class<?> c = loader.findClass("Student.class");
        Object ob = c.newInstance();
        Method md = c.getMethod("show");
        md.invoke(ob);

    }


}