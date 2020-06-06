package fr.iut.projet.projettutorearchetype.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.AccessDeniedException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.iut.projet.projettutorearchetype.exceptions.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResponseMessage responseMessage = new ResponseMessage("Unauthorized", "You are not authorized to access to this resource", HttpStatus.UNAUTHORIZED);
        logger.error("Unauthorized error: {}", e.getMessage());

        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String json = objectMapper.writeValueAsString(responseMessage);
            httpServletResponse.setContentType("application/json");
            writer.println(json);
        }
        catch (IOException ioException) {
            httpServletResponse.setContentType("text/plain");
            writer.println("You are not authorized to access to this resource");
        }

        writer.flush();
    } 

    @ExceptionHandler(value = AccessDeniedException.class)
    public void commence(HttpServletRequest request, HttpServletResponse httpServletResponse,
                         AccessDeniedException e) throws IOException {
        ResponseMessage responseMessage = new ResponseMessage("Insufficient role", "You are not authorized to access this resource", HttpStatus.FORBIDDEN);
        logger.error("Unauthorized error: {}", e.getMessage());
        System.out.println("access denied");

        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String json = objectMapper.writeValueAsString(responseMessage);
            httpServletResponse.setContentType("application/json");
            writer.println(json);
        }
        catch (IOException ioException) {
            httpServletResponse.setContentType("text/plain");
            writer.println("You are not authorized to access to this resource");
        }

        writer.flush();
    }

}