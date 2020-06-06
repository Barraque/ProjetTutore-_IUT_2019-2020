package fr.iut.projet.projettutorearchetype.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class ResponseMessage {
    private String title;
    private String content;
    private HttpStatus status;

    public static ResponseEntity<ResponseMessage> build(String title, String content, HttpStatus status) {
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(title, content, status), status);
    }
}
