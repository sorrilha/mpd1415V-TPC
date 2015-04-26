/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isel.mpd.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Sorrilha (a37331)
 */
public  class HttpGw {

    private String URI;
    private HttpEntity entity;
    public final  InputStream stream ;
    private CloseableHttpClient httpClient;
    private CloseableHttpResponse response;
    
    public HttpGw(String URI) {
        this.URI = URI;
            /*
            * Method: HttpGet
            */
            httpClient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(URI);
            /*
            * HttpResponse
            */
            try{
                response = httpClient.execute(httpget);
                entity = response.getEntity();
                stream = entity.getContent();
                
            } 
      
       catch(IOException e){
           disposeStreams();
           throw new RuntimeException(e);
       }
            
    }
    
    public void disposeStreams()
    {
        try {
            httpClient.close();
            response.close();
            stream.close();
        } catch (IOException ex) {
           throw new RuntimeException(ex);
        }
        
    }
    
}
