
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

class App{

   public static void main (String args[]) {

        
   String classpath = System.getProperty("java.class.path");
   System.out.println(classpath);
   //String[] classPathValues = classpath.split(File.pathSeparator);
   //System.out.println(Arrays.toString(classPathValues));
         
   }
}