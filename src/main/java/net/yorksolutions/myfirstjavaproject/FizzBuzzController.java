package net.yorksolutions.myfirstjavaproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FizzBuzzController {
    @GetMapping("/fizzbuzz")
    String fizzbuzz(@RequestParam int input) {
        return null;
    }
}
