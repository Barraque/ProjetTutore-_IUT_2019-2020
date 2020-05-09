package fr.iut.projet.projettutorearchetype.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.NoSuchElementException;

@ControllerAdvice(value = "fr.iut.projet.projettutorearchetype.controller")
public class GlobalExceptionHandler {

    /**
     * Transforme une exception "pas d'élément trouvé" en erreur 404
     * @param nsee Objet de l'exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(
            final NoSuchElementException nsee
    ) {
        return new ResponseEntity<>(
                "Resource not found -> " + nsee.getMessage(),
                HttpStatus.NOT_FOUND
        );
    }

    /**
     * Transforme une exception "objet null" en erreur 500
     * @param npe Objet de l'exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(
            final NullPointerException npe
    ) {
        return new ResponseEntity<>(
                "Resource on server failed : " + npe.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    /**
     * Transforme une exception "mauvaise méthode" en erreur 415
     * @param mnae Objet de l'exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = MethodNotAllowedException.class)
    public ResponseEntity<String> handleMethodNotAllowedException(
            final MethodNotAllowedException mnae
    ) {
        return new ResponseEntity<>(
                "Method utilized is not permitted : " + mnae.getMessage(),
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    /**
     * Transforme une exception "Argument illégal" en erreur 400
     * @param iae Objet de l'exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(
            final IllegalArgumentException iae
    ) {
        return new ResponseEntity<>(
                "Illegal argument used : " + iae.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }

    /**
     * Transforme une exception "État illégal" en erreur 400
     * @param ise Objet de l'exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(
            final IllegalStateException ise
    ) {
        return new ResponseEntity<>(
                "Illegal state used : " + ise.getMessage(),
                HttpStatus.BAD_REQUEST
        );
    }


    /**
     * Transforme une exception "nom d'utilisateur non trouvé" en erreur 404
     * @param unfe Objet de l'exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(
            final UsernameNotFoundException unfe
    ) {
        return new ResponseEntity<>(
                "Nom d'utilisateur non trouvé : [" + unfe.getMessage() + "]",
                HttpStatus.NOT_FOUND
        );
    }

}
