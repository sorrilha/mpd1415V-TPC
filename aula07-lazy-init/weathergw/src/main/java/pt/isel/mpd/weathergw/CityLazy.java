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
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class CityLazy {
    
    private final String cityName;
    private Supplier<List<WeatherInfo>> history;
    public static final  List<List<WeatherInfo>> wi = new LinkedList<>();
    
    public CityLazy(String cityName){
        this.cityName = cityName;
        this.history = () ->{
            List<WeatherInfo> tmp =  WeatherParser.parseWeather();
            wi.add(tmp);
            history = () -> tmp;
            return tmp;
        };
    }
    
    public List<WeatherInfo> getWeatherHistory(){
        return history.get();
    }
}
