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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.ClassLoader.getSystemResourceAsStream;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class FileParser {

    /**
     * TPC: Implementar um Iterable lazy que retorna as linhas do resource 
     * na forma de Strings.
     * NÃO podem instanciar arrays nem colecções sauxiliares.
     */
    public static Iterable<String> parseResourceAsIterable(String path) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(
                getSystemResourceAsStream(path)));
        
        return new Iterable<String> ()
        {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>(){
                    boolean ready= false;
                    String line; 
                    @Override
                    public boolean hasNext() {
                        if(ready)
                            return true;
                        try {
                            String s = in.readLine();
                            if(s!= null)
                            {
                                line = s;
                                ready = true;
                            }
                        } catch (IOException ex) {
                            return false;
                        }
                        return ready;
                    }

                    @Override
                    public String next() {
                        String aux;
                        if(!hasNext())
                            throw new NoSuchElementException();
                        else
                        {
                            aux = line;
                            line = null;
                            ready=false;
                        }
                        return aux;
                    }
                };
            }
        };
        /* SUBSTITUIR O CÓDIGO ABAIXO PELO ITERABLE -- TPC*/
       /* List<String> res = new ArrayList<>();
        String line = in.readLine();
        while(line != null){
            res.add(line);
            line = in.readLine();
        }
        return res;
        */
    }
}
