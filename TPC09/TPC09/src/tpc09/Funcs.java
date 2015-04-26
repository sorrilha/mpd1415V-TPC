/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tpc09;

import java.util.function.Function;

public class Funcs<K,V> implements Function<K, V>{

    Function<K, V> _func;
    public Funcs(Function<K,V> func)
    {
        _func = func;
    }
    
    @Override
    public V apply(K t) {
        return _func.apply(t);
    }
    
    public Function<K,V> myDefault(V v1){
        return new Funcs<>((arg)->{
            try{
                return apply(arg);
            }
            catch(IllegalArgumentException e){
                return v1;
            }
        });
    }
    
}
