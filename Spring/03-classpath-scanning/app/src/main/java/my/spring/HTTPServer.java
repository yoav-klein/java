package my.spring;

import org.springframework.stereotype.Service;

@Service("myHttp")
public class HTTPServer {
   TCP tcp;
   int version;

    HTTPServer(TCP tcp) {
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
