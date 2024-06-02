# Sessions
---

In Servlet API, you can call HttpServletRequest.getSession(true), and this will create a session
using Cookies. So the next request that the user will send will contain that cookie.

## Usage
Deploy the war file to a Tomcat server, and then browse in a browser to `http://<tomcat-server-domain>:8080/app/hello`