import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.FileUtils;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HelloWorld {

    static Logger log = LogManager.getLogger(HelloWorld.class.getName());


    public static void main(String[] args) {

        log.info(System.getenv("JAVA_HOME"));
        log.info(System.getProperty("java.home"));

//        checkClassLoader(HelloWorld.class);
//        checkClassLoader(Student.class);
//        checkClassLoader(Logger.class);


//        testLoaderOrder();

        testClassLoader(); // each class loader two time , it will be different;


       //testLoaderResource();

    }

    private static void testLoaderResource() {

        CCLoader cl = new CCLoader(HelloWorld.class.getClassLoader());

        InputStream stream = cl.getResourceAsStream("test.xml");


        String content = readLineByLineJava8(stream);
        log.info( content );
        log.info( "-------------");


    }


    private static String readLineByLineJava8(InputStream stream)
    {
      return new BufferedReader(new InputStreamReader(stream))
                .lines().collect(Collectors.joining("\n"));
    }





    private static void testClassLoader() {


        Class type1 = null;
        Class type2 = null;


        type1 = loaderClass(new CCLoader(HelloWorld.class.getClassLoader()));
        type2 = loaderClass(new CCLoader(HelloWorld.class.getClassLoader()));


        if (type1 == type2) {
            log.info("they are same");
        } else {
            log.info("they are different");
        }

        try {
            Object obj = type1.newInstance();

            Itest student = (Itest) obj;
            log.info( student.toString());
            log.info( student.getName());

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

    private static Class loaderClass(ClassLoader ccLoader) {


        try {
            return ccLoader.loadClass("Student");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private  void testLoaderOrder() {

        log.info(HelloWorld.class.getCanonicalName());
        log.info(new Student().getName());
    }


    private  void checkClassLoader(Class type) {

        log.info("show the hierarchy of class " + type.getName());
        log.info("current class loader" + type.getClassLoader());


        for (ClassLoader cl = type.getClassLoader(); cl != null; cl = cl.getParent()) {
            log.info(cl);
        }

        log.info("");

    }
}