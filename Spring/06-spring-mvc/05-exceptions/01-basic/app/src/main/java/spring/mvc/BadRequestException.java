
package spring.mvc;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "You bad boy...")
public class BadRequestException extends Exception {

}
