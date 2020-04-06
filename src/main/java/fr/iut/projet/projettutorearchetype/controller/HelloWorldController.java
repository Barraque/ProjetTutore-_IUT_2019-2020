package fr.iut.projet.projettutorearchetype.controller;

import fr.iut.projet.projettutorearchetype.constants.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {


    @GetMapping(Constants.apiConstant+"helloWorld")
    public String helloWorld (){return "Hello World !";}


}
