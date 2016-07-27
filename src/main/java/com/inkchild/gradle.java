package com.inkchild;

import org.asynchttpclient.*;
import java.util.concurrent.Future;

public class gradle {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();
            Future<Response> f = asyncHttpClient.prepareGet("http://www.iammaze.com/").execute();
            Response r = f.get();
            System.out.println("Status Code : " + r);
            
            asyncHttpClient.close();
        } catch(Exception e) {
            System.out.println("Something went wrong.");
        }
    }
}
