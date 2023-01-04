package com.test.demo.services;

import com.test.demo.exceptions.DivisionByZeroException;
import com.test.demo.exceptions.LessThanZeroRootException;
import com.test.demo.exceptions.UnsupportedMathOperationException;

public class MathServices {
    public static Double convertToDouble(String n) throws Exception{
        if (!MathServices.isNumeric(n)){
            throw new UnsupportedMathOperationException("Please, set a numeric value!");
        }
        String number = n.replaceAll(",", ".");
        if (isNumeric(n)) return Double.parseDouble(number);
        return 0D;
    }

    public static boolean isNumeric(String n) {
        if (n == null) return false;
        String number = n.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static Double sum(Double n1, Double n2){
        return n1 + n2;
    }

    public static Double sub(Double n1, Double n2){
        return n1 - n2;
    }

    public static Double mul(Double n1, Double n2){
        return n1 * n2;
    }

    public static Double div(Double n1, Double n2) throws Exception{
        if (n2 == 0) { throw new DivisionByZeroException("Divisão por zero não é permitida!"); }
        return n1 / n2;
    }

    public static Double raiz(Double n1) throws Exception{
        if (n1 < 0 ) { throw new LessThanZeroRootException("Raiz de um número menor que zero não é suportada!"); }
        return Math.sqrt(n1);
    };
}
