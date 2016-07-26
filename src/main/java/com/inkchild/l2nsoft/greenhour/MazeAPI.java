/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inkchild.l2nsoft.greenhour;

// include the Apache library files
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import static javafx.css.StyleOrigin.USER_AGENT;

import org.apache.http.HttpEntity;
import static org.apache.http.HttpHeaders.USER_AGENT;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.portable.ResponseHandler;
import org.omg.DynamicAny.NameValuePair;

/**
 *
 * @author Mazhar
 */
public class MazeAPI {
    // the access token
    public static String accessToken;
    // the refresh token
    public static String refreshToken;
    // *************************************************************************
    // API client credential here   |   they are fixed
    // *************************************************************************
    // client id
    private final String client_id = "1_3zoycu8v3juow80cw0cowgows0c044ks488owos0osocg0gcw8";
    // client secret key
    private final String client_secret = "57mzqs6u53c4wssgoc0s4wgocc40kc040s440ko0ssg4sswsg4";
    
    // the base url of the API
    private String baseUrl = "http://green.rannabanna.org/";
    
    public static String getUniqID ()
    {
        InetAddress ip;
	try {
            ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Current MAC address : ");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
            }
            System.out.println(sb.toString());
	} catch (UnknownHostException e) {	
            e.printStackTrace();
	} catch (SocketException e){
            e.printStackTrace();
	}
    }
    
    /**
     * @name MazeAPI
     * @author Mazhar Ahmed
     * 
     * the constructor
     * 
     * @param username
     * @param password 
     */
    public void MazeAPI(String username, String password) {
        String theUrl = baseUrl + "oauth/v2/token";
    }
    
    public void MazeAPI(){
        // it's already logged in, let's verify by refreshing the key
    }
    
    public void get(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(url);

            System.out.println("Executing request " + httpget.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } finally {
            httpclient.close();
        }
    }
    
    /**
     * @name post
     * @author Mazhar Ahmed <info@mazharahmed.me>
     * 
     * handles the post request
     * 
     * @param url
     * @param values
     * @throws InterruptedException
     * @throws UnsupportedEncodingException 
     */
    public void post(String url, List<HttpData> values) throws InterruptedException, UnsupportedEncodingException {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        
        // Start the client
        httpclient.start();
    
        // One most likely would want to use a callback for operation result
        final CountDownLatch latch1 = new CountDownLatch(1);
        HttpPost request = new HttpPost(url);
        
        // add header
	request.setHeader("User-Agent", USER_AGENT);

	List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        
        for (HttpData element : values) {
            urlParameters.add(new BasicNameValuePair(element.key, element.value));
        }

	request.setEntity(new UrlEncodedFormEntity(urlParameters));
        
        httpclient.execute(request, new FutureCallback<HttpResponse>() {

            public void completed(final HttpResponse response) {
                latch1.countDown();
                System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
                //JSONObject jsonObj = new JSONObject(EntityUtils.toString(response.getEntity()));
                    
            }

            public void failed(final Exception ex) {
                latch1.countDown();
                System.out.println(request.getRequestLine() + "->" + ex);
            }

            public void cancelled() {
                latch1.countDown();
                System.out.println(request.getRequestLine() + " cancelled");
            }

        });
        latch1.await();

    }
    
}
