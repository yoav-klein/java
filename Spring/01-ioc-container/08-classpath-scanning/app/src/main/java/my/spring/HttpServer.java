package my.spring;

import org.springframework.stereotype.Service;

@Service("myHttp") // you can provide a name for the bean. If you don't, the non-capitalized name of the class will be
// the name of the bean. In this case, it'll be h
public class HttpServer {
   TCP tcp;
   int version;

   HttpServer(TCP tcp) {
         this.tcp = tcp;
         System.out.println("HTTPServer constructor");
    }

   public void sendMessage() {
      System.out.println("HTTPServer Sending");
      tcp.sendMessage("Hello World");
   }

   public void setVersion(int version) {
      this.version = version;
   }
 }
