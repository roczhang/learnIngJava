import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoaderDemo extends ClassLoader {
    private static final Logger log = LogManager.getLogger("HelloWorld");

    @Override
      public Class<?> findClass(String name) {
         byte[] bt = loadClassData(name);
         return defineClass(name, bt, 0, bt.length);
      }
      private byte[] loadClassData(String className) {
        //read class
          String path = "C:\\perforce\\B1OD\\1.1_DEV\\src\\B1OD_WAVE2\\Code01\\Code-01\\build\\classes\\main\\Student.class";
          log.info(getClass().getClassLoader().getResource(".") );


          InputStream is = getClass().getClassLoader().getResourceAsStream(path);
        ByteArrayOutputStream byteSt = new ByteArrayOutputStream();
        //write into byte
        int len =0;
        try {
                     while((len=is.read())!=-1){
                           byteSt.write(len);
                      }
               } catch (IOException e) {
                     e.printStackTrace();
               }
        //convert into byte array
        return byteSt.toByteArray();
     }
    
}