/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpc09;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TPC09 {

    public static void main(String[] args) {
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0772);
        rates.put("SEK", 9.379);
        rates.put("BRL", 3.2328);
        rates.put("AUD", 1.3937);
        Function<String, Double> ratesFn = formap(rates);
        double australianRate = ratesFn.apply("AUD");
        System.out.println("australianRate " + australianRate);
        try {
            double canadianRate = ratesFn.apply("CAD"); //illegalArgumentException
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        Function<String, Double> ratesFn2 = formap(rates).myDefault(0.0);
        australianRate = ratesFn2.apply("AUD");
        System.out.println("australianRate " + australianRate);
        double canadianRate = ratesFn2.apply("CAD");
        System.out.println("canadianRate " + canadianRate);

    }

    public static <K, V> Funcs formap(Map<K, V> map) {
        return new Funcs<>(
                (K arg) -> {
                    V v = map.get(arg);
                    if (v == null) {
                        throw new IllegalArgumentException();
                    }
                    return v;
                });
    }

}
