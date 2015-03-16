/*
 * Copyright (C) 2015 Miguel Gamboa at CCISEL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.isel.mpd.weathergw;

import java.text.ParseException;
import java.util.List;


interface Predicate<T> {

    boolean get(T arg);
}

interface Supplier<T> {

    T get();
}

interface Runnable {

    void run();
}

public class App {

    public static <T> T fetch(Supplier<T> s) {
        return s.get();
    }

    public static String fetchString(Supplier<String> s) {
        return s.get();
    }

    public static void execute(Runnable r) {
        r.run();
    }

    public static Supplier<Integer> getter() {
        return () -> {
            return 5;
        };
    }//Faltavam só uns ';'

    public static void main(String[] args) {
    /* ------------------   TPC 03 -----------------  
        1	() -> {}
		Statement vazio, é obrigatório haver um return quando fazemos um bloco de instruções. 
	
	2	(Integer i) -> return "Ramirez" + i;
 		Tipo de retorno incompativel (o return embora não cause erro não é necessário, está implícito).
	
        3	() -> "Ze Manel"
	Está ok. Falta apenas o ‘ ; ’
    
	4	(String s) -> {"Godzilla";}
	Falta o return dentro do bloco.

	5	() -> {return "Josefina";}
	Está ok.

    */  
        
    /* ------------------   TPC 04 -----------------*/
        //Funciona;
        System.out.println(fetchString(() -> {
            return "Ola";
        })); // Passagem de Lambda por parametro

    //Não consigo corrigir este;
        //System.out.println(execute(() -> {})); // Passagem de Lambda por parametro
        
    //Um predicado deveria devolver apenas boolean, int é incompativel. ;
        Predicate<WeatherInfo> evalTempC = (WeatherInfo w) -> w.tempC; // afectacao de Lambda a variavel
        Predicate<WeatherInfo> evalTemp = (WeatherInfo w) -> true;

    // Falta chamar o método get
        assert getter().equals(5) : "Getter not evaluating to 5 number"; // Lambda como retorno do metodo
        assert getter().get().equals(5) : "Getter not evaluating to 5 number"; // Lambda como retorno do metodo
    }

}
