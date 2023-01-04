package com.test.demo.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.test.demo.services.MathServices;

@RestController
public class MathController {

    @GetMapping("/sum/{n1}/{n2}")
    public Double sum(
        @PathVariable(value = "n1") String n1,
        @PathVariable(value = "n2") String n2
    ) throws Exception  {
        return MathServices.sum(
            MathServices.convertToDouble(n1),
            MathServices.convertToDouble(n2)
        );
    }

    @GetMapping("/sub/{n1}/{n2}")
    public Double sub(
        @PathVariable(value = "n1") String n1,
        @PathVariable(value = "n2") String n2
    ) throws Exception  {
        return MathServices.sub(
            MathServices.convertToDouble(n1),
            MathServices.convertToDouble(n2)
        );
    }

    @GetMapping("/mul/{n1}/{n2}")
    public Double mul(
        @PathVariable(value = "n1") String n1,
        @PathVariable(value = "n2") String n2
    ) throws Exception {        
        return MathServices.mul(
            MathServices.convertToDouble(n1),
            MathServices.convertToDouble(n2)
        );
    }

    @GetMapping("/div/{n1}/{n2}")
    public Double div(
        @PathVariable(value = "n1") String n1,
        @PathVariable(value = "n2") String n2
    ) throws Exception {
        return MathServices.div(
            MathServices.convertToDouble(n1),
            MathServices.convertToDouble(n2)
        );
    }

    @GetMapping("/raiz/{n1}")
    public Double raiz(
        @PathVariable(value = "n1") String n1
    ) throws Exception {
        return MathServices.raiz(
            MathServices.convertToDouble(n1)
        );
    }

}
