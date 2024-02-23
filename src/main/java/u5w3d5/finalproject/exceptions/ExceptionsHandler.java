package u5w3d5.finalproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import u5w3d5.finalproject.payload.error.ErrorDTO;
import u5w3d5.finalproject.payload.error.ErrorDTOWithList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
  @ExceptionHandler(BadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
  public ErrorDTOWithList handleBadRequest(BadRequestException ex) {
    List<String> errMessage = new ArrayList<>();
    if (ex.getErrorsList() != null)
      errMessage = ex.getErrorsList().stream().map(err -> err.getDefaultMessage()).toList();
    return new ErrorDTOWithList(ex.getMessage(), LocalDateTime.now(), errMessage);
  }

  @ExceptionHandler(UnauthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED) //401
  public ErrorDTO handleUnauthorized(UnauthorizedException e) {
    return new ErrorDTO(e.getMessage(), LocalDateTime.now());
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN) // 403
  public ErrorDTO handleAccessDenied(AccessDeniedException ex) {
    return new ErrorDTO("Il tuo ruolo non permette di accedere a questa funzionalità!", LocalDateTime.now());
  }

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)   //404 Not Found
  public ErrorDTO HandleNotFound(NotFoundException ex) {
    return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   //500 Internal Server Error
  public ErrorDTO handleGenericError(Exception ex) {
    ex.printStackTrace();
    return new ErrorDTO("*** problema Lato Server *** Giuro che risolveremo!!!!", LocalDateTime.now());
  }
}
