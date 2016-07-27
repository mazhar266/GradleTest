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
            Future<Response> f = asyncHttpClient.preparePost("http://green.rannabanna.org/oauth/v2/token")
                    .addFormParam("client_id", "1_3zoycu8v3juow80cw0cowgows0c044ks488owos0osocg0gcw8")
                    .addFormParam("client_secret", "57mzqs6u53c4wssgoc0s4wgocc40kc040s440ko0ssg4sswsg4")
                    .addFormParam("grant_type", "password")
                    .addFormParam("username", "maz")
                    .addFormParam("password", "123456")
                    .execute();
            Response r = f.get();
            System.out.println("Status Code : " + r);
            
            asyncHttpClient.close();
        } catch(Exception e) {
            System.out.println("Something went wrong.");
        }
    }
}
