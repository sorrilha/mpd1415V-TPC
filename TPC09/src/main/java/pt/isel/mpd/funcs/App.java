package pt.isel.mpd.funcs;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;



class App {

    public static void main(String[] args) {

        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0772);
        rates.put("SEK", 9.379);
        rates.put("BRL", 3.2328);
        rates.put("AUD", 1.3937);
        Function<String, Double> ratesFn = forMap(rates);
        double australianRate = ratesFn.apply("AUD"); // => 1.3937
        System.out.println("australianRate" + australianRate);
        double canadianRate = ratesFn.apply("CAD"); // => IllegalArgumentException
        Function<String, Double> ratesFn2 = forMap(rates).default(0);
        australianRate = ratesFn2.apply("AUD"); // => 1.3937
        System.out.println("australianRate"+ australianRate);
        canadianRate = ratesFn2.apply("CAD"); // => 0
        System.out.println("canadianRate"+ canadianRate);
    }

    {
        public static <K, V> Function<K, V> forMap(Map<K, V> map) {
       return new Funcs<K,V>(){
           (arg) -> {
            V v = map.get(arg);
            if (v == null) {
                throw new IllegalArgumentException();
            };
            return v;
        };      
    }
    }
    
    

}


