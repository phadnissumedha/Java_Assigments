/*
Question 4 : Write an API “/mycalc” to accept a list of numbers and an operation name in an input
request. Refer to earlier example for list of permitted operation names (e.g. mean value,
minimum value, maximum value etc.). The response should provide information about the
operation executed and the result of an operation. [JSON array handling, 4 hours]
*/

package com.example.solution1.controller;

import java.util.List;
import java.util.OptionalDouble;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {

    public static class calcRequest {
        private String operation;
        private List<Double> numList;

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public List<Double> getnumList() {
            return numList;
        }

        public void setnumList(List<Double> numList) {
            this.numList = numList;
        }
    }

    public static class calcResponse {
        private String operation;
        private double result;

        public calcResponse(String operation, double result) {
            this.operation = operation;
            this.result = result;
        }

        public String getOperation() {
            return operation;
        }

        public double getResult() {
            return result;
        }
    }

    @PostMapping("/mycalc")
    public Object mycalc(@RequestBody calcRequest request) {
        List<Double> numberDoubles = request.getnumList();
        double result = 0;

        switch (request.getOperation().toLowerCase()) {

            case "mean":
            case "average":
                OptionalDouble average = numberDoubles.stream().mapToDouble(Double::doubleValue).average();
                if (average.isPresent()) {
                    result = average.getAsDouble();
                }
                break;

            case "max":
            case "maximum":
                result = numberDoubles.stream().mapToDouble(Double::doubleValue).max().orElse(result);
                break;

            case "min":
            case "minimum":
                result = numberDoubles.stream().mapToDouble(Double::doubleValue).min().orElse(result);
                break;
            default:
                return "error : Invalid Operation";
        }
        return new calcResponse(request.getOperation(), result);
    }
}
