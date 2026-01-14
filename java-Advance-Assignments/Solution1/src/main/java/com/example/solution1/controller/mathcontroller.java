package com.example.solution1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mathcontroller {
    public static class SumRequest {
        private String operation;
        private double number1;
        private double number2;

        public String getoperation() {
            return operation;
        }

        public void setoperation(String operation) {
            this.operation = operation;
        }

        public double getnumber1() {
            return number1;
        }

        public void setnumber1(double number1) {
            this.number1 = number1;
        }

        public double getnumber2() {
            return number2;
        }

        public void setnumber2(double number2) {
            this.number2 = number2;
        }
    }

    @PostMapping("/mysum")

    public String mySum(@RequestBody SumRequest request) {
        double result = 0;

        switch (request.getoperation().toLowerCase()) {

            case "add":
            case "sum":
                result = request.getnumber1() + request.getnumber2();
                break;

            case "subtract":
            case "minus":
                result = request.getnumber1() - request.getnumber2();
                break;

            case "multiply":
            case "product":
                result = request.getnumber1() * request.getnumber2();
                break;

            case "divide":
                if (request.getnumber2() != 0) {
                    result = request.getnumber1() / request.getnumber2();
                } else {
                    return "error : Denominator cannot be zero";
                }
                break;

            case "power":
                result = Math.pow(request.getnumber1(), request.getnumber2());
                break;

            case "modulus":
            case "mod":
                if (request.getnumber2() == 0) {
                    return "error : Denominator cannot be zero";
                }
                result = request.getnumber1() % request.getnumber2();
                break;

            case "max":
            case "maximum":
                result = Math.max(request.getnumber1(), request.getnumber2());
                break;

            case "min":
            case "minimum":
                result = Math.min(request.getnumber1(), request.getnumber2());
                break;

            default:
                return "error : Invalid Operation";
        }
        return " Result of Operation " + request.getoperation() + "is : " + result;
    }
}