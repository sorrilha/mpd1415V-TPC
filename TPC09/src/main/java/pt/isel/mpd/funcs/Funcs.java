/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isel.mpd.funcs;

import java.util.Map;
import java.util.function.Function;



public class Funcs<K, V> implements Function<K, V> {

   
    Function<K, V> func;

    public Funcs(Function<K, V> func) {
        this.func = func;
    }

      public static <K, V> Function<K, V> forMap(Map<K, V> map) {
       return new FuncsFuncs<K,V>(){
           (arg) -> {
            V v = map.get(arg);
            if (v == null) {
                throw new IllegalArgumentException();
            };
            return v;
        };      
    }
       
    
    @Override
    public V apply(K key) {
        return func.apply(key);
    }

    public Function<K, V> default(V v1){
            return new Funcs<K, V>(func) {
            @Override
            public V apply(K key) {
                try {
                    return func.apply(key);
                } catch (IllegalArgumentException e) {
                    return v1;
                }
            };
        };
    }

}
