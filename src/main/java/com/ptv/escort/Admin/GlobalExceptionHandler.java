//package com.ptv.escort.Admin;
//
//
//import java.io.IOException;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ptv.escort.Response.ApiResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//
//    @ExceptionHandler(RuntimeException.class)
//    @ResponseBody
//    public ResponseEntity<?> springHandleNotFound(HttpServletResponse response, Exception ex) throws IOException {
////        ApiResponse<String> error = new ApiResponse<>();
////        error.setResponse(response.getStatus());
////        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
////        error.setMessage(ex.getMessage());
////        error.setError("Error Found");
////        error.setResponsecode("99");
//        return ResponseEntity.ok(error);
//    }
//
//}
