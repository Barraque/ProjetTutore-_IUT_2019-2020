package fr.iut.projet.projettutorearchetype.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@ControllerAdvice(value = "fr.iut.projet.projettutorearchetype.controller")
public class GlobalExceptionHandler {

    @Data
    @AllArgsConstructor
    public static class ResponseMessage {
        private String title;
        private String content;
        private HttpStatus status;

        public static ResponseEntity<ResponseMessage> build(String title, String content, HttpStatus status) {
            return new ResponseEntity<ResponseMessage>(new ResponseMessage(title, content, status), status);
        }
    }

    /**
     * Transforme une exception "pas d'élément trouvé" en erreur 404
     * @param nsee Objet de l'exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<ResponseMessage> handleNoSuchElementException(
            final NoSuchElementException nsee
    ) {
        return ResponseMessage.build("Resource not found", nsee.toString(), HttpStatus.NOT_FOUND);
    }

    /**
     * Transforme une exception "objet null" en erreur 500
     * @param npe Objet de l'exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ResponseMessage> handleNullPointerException(
            final NullPointerException npe
    ) {
        return ResponseMessage.build("Internal Server Error", "An error occurred that the server can't handle", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Transforme une exception "mauvaise méthode" en erreur 415
     * @param mnae Objet de l'exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = MethodNotAllowedException.class)
    public ResponseEntity<ResponseMessage> handleMethodNotAllowedException(
            final MethodNotAllowedException mnae
    ) {
        String message = "[" + mnae.getHttpMethod() + "] not permitted";
        return ResponseMessage.build("HTTP Method not permitted", message, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Transforme une exception "Argument illégal" en erreur 400
     * @param iae Objet de l'exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ResponseMessage> handleIllegalArgumentException(
            final IllegalArgumentException iae
    ) {
        return ResponseMessage.build("Internal Server Error", "An error occurred that the server can't handle", HttpStatus.NOT_FOUND);
    }

    /**
     * Transforme une exception "État illégal" en erreur 400
     * @param ise Objet de l'exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<ResponseMessage> handleIllegalStateException(
            final IllegalStateException ise
    ) {
        return ResponseMessage.build("Internal Server Error", "An error occurred that the server can't handle", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Transforme une exception "nom d'utilisateur non trouvé" en erreur 404
     * @param unfe Objet de l'exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleUsernameNotFoundException(
            final UsernameNotFoundException unfe
    ) {
        return ResponseMessage.build("Wrong username", "Provided username [" + unfe.getMessage() + "] doesn't exists", HttpStatus.NOT_FOUND);

    }

}
