import java.lang.management.ManagementFactory;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class App {
    public static void main(String[] args) {
        Counter counter = new Counter();
        System.out.println("Program starts...");
        try {
            
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName name = new ObjectName("com.example:type=Counter");
            mbs.registerMBean(counter, name);

        } catch (MalformedObjectNameException | InstanceAlreadyExistsException |
                MBeanRegistrationException | NotCompliantMBeanException e) {
                    System.out.println("Error registering MBean");
                    System.out.println(e);
            // handle exceptions
        }
        try {

            while(true) {

                Thread.sleep(3000);
                System.out.println("Current value: " + counter.getValue());

            }
            // Sleep for 3 seconds (3000 milliseconds)
        } catch (InterruptedException e) {
            System.out.println("Sleep was interrupted.");
        }

        System.out.println("Program resumes after sleep.");
    }
}
