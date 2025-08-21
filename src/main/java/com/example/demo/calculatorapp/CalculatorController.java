package com.example.demo.calculatorapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/")
    public String home() {
        return "home"; // -> templates/home.html
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("num1") double n1,
                            @RequestParam("num2") double n2,
                            @RequestParam("operation") String operation,
                            Model model) {

        Double result = null;
        String error = null;

        try {
            switch (operation) {
                case "add" -> result = calculatorService.add(n1, n2);
                case "sub" -> result = calculatorService.subtract(n1, n2);
                case "mul" -> result = calculatorService.multiply(n1, n2);
                case "div" -> result = calculatorService.divide(n1, n2);
                default -> error = "Invalid operation.";
            }
        } catch (ArithmeticException ex) {
            error = ex.getMessage();
        }

        model.addAttribute("num1", n1);
        model.addAttribute("num2", n2);
        model.addAttribute("operation", operation);
        model.addAttribute("result", result);
        model.addAttribute("error", error);

        return "result"; // -> templates/result.html
    }
}
