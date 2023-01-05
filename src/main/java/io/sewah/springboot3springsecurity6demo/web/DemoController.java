package io.sewah.springboot3springsecurity6demo.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    @GetMapping("/")
    public ResponseEntity<?> greet(){
        return ResponseEntity.ok("What good!");
    }
}
