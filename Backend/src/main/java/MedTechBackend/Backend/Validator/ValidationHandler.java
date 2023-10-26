//package MedTechBackend.Backend.Validator;
//
//import java.sql.SQLIntegrityConstraintViolationException;
//import java.util.HashMap;
//import java.util.Map;
//
//import MedTechBackend.Backend.exception.ResourceNotFoundException;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//
//
//@ControllerAdvice
//public class ValidationHandler extends ResponseEntityExceptionHandler{
//
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(
//            MethodArgumentNotValidException ex,
//            HttpHeaders headers,
//            HttpStatus status,
//            WebRequest request) {
//
//        Map<String, String> errors = new HashMap<>();
//
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String message = error.getDefaultMessage();
//            errors.put(fieldName, message);
//        });
//
//        return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<String>
//    handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
//        return new
//                ResponseEntity<String>(resourceNotFoundException.getMessage(),HttpStatus.BAD_REQUEST); }
//
//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ResponseEntity<Object> handleConstraintViolation(SQLIntegrityConstraintViolationException ex ,WebRequest request){
//        return new ResponseEntity<>("OOPS!!! duplicate entry of email got detected",HttpStatus.BAD_REQUEST);
//    }
//}