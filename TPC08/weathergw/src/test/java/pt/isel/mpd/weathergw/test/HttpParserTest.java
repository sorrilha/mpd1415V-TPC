/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isel.mpd.weathergw.test;

import java.io.IOException;
import junit.framework.Assert;
import junit.framework.TestCase;
import pt.isel.mpd.weathergw.WeatherInfo;
import pt.isel.mpd.weathergw.WeatherParserFromHttp;

/**
 *
 * @author Sorrilha (a37331)
 */
public class HttpParserTest extends TestCase{
     public void test_read_all_lines_from_http() throws IOException {
        WeatherParserFromHttp  wp = new WeatherParserFromHttp("http://api.worldweatheronline.com/free/v2/past-weather.ashx?q=Lisbon&format=csv&date=2015-2-15&enddate=2015-3-15&tp=24&key=e566e1fd4829191096c3151a16365");
        Iterable<WeatherInfo> lines = wp.parseWeather();
        int count = 0;
        for (WeatherInfo wi : lines) {
            count++;
        }
        Assert.assertEquals(29, count);
    }
}
