package com.inkchild.l2nsoft.greenhour;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static org.apache.http.HttpHeaders.USER_AGENT;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 
 * @author Mazhar Ahmed
 */
public class loginDialog {
    // the email field
    @FXML private TextField email;
    // the password field
    @FXML private PasswordField password;
    // the login button
    @FXML private Button login;
    
    // the main stage
    Stage stage;
    
    /**
     * @name loginAction
     * @author Mazhar Ahmed <info@mazharAhmed.me>
     * 
     * called when the button is pressed
     */
    @FXML
    protected void loginAction() {
        System.out.println(email.getText());
        System.out.println(password.getText());
        
        try {
            // load the loader dialog
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loader.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            // get the current stage
            stage = (Stage) login.getScene().getWindow();
            stage.setScene(new Scene(root));
            
            System.out.println(stage.getTitle());
            
            // =================================================================
            //           API WORK HERE
            // =================================================================
            
            CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        
            // Start the client
            httpclient.start();
            
            // One most likely would want to use a callback for operation result
            //final CountDownLatch latch1 = new CountDownLatch(1);
            HttpPost request = new HttpPost("http://localhost/test.php");

            // add header
            request.setHeader("User-Agent", USER_AGENT);

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();

            urlParameters.add(new BasicNameValuePair("client_id", ApiConfig.client_id));
            urlParameters.add(new BasicNameValuePair("client_secret", ApiConfig.client_secret));
            
            urlParameters.add(new BasicNameValuePair("grant_type", "password"));
            urlParameters.add(new BasicNameValuePair("username", email.getText()));
            urlParameters.add(new BasicNameValuePair("password", password.getText()));

            request.setEntity(new UrlEncodedFormEntity(urlParameters));

            httpclient.execute(request, new FutureCallback<HttpResponse>() {
                
                private Stage innerStage;

                public void completed(final HttpResponse response) {
                    //latch1.countDown();
                    try {
                        //System.out.println(EntityUtils.toString(response.getEntity()));
                        
                        JsonParserFactory factory = JsonParserFactory.getInstance();
                        JSONParser parser = factory.newJsonParser();
                        Map jsonMap = parser.parseJson(EntityUtils.toString(response.getEntity()));
                        
                        // save the refresh token here
                        System.out.println(jsonMap.get("refresh_token"));
                        
                        
                        /*
                        
                        int myVariable = 1;

                        myButton.addActionListener(new ActionListener() {
                            private int anonVar;
                            public void actionPerformed(ActionEvent e) {
                                // How would one access myVariable here?
                                // It's now here:
                                System.out.println("Initialized with value: " + anonVar);
                            }
                            private ActionListener init(int var){
                                anonVar = var;
                                return this;
                            }
                        }.init(myVariable)  );

                        */
                        
                        // load the admin dialog
                        System.out.println("1");
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin.fxml"));
                        System.out.println("2");
                        Parent root = (Parent) fxmlLoader.load();
                        System.out.println("3");
                        // get the current stage
                        innerStage.setScene(new Scene(root));
                        System.out.println("4");
                        System.out.println(innerStage.getTitle());
                        
                        System.out.println("logged in successfully");
                        
                        //JsonReader jsonReader = Json.createReader(response.getEntity()));
                    } catch (Exception ex)
                    {
                        
                    }

                }

                public void failed(final Exception ex) {
                    //latch1.countDown();
                    System.out.println(request.getRequestLine() + "->" + ex);
                }

                public void cancelled() {
                    //latch1.countDown();
                    System.out.println(request.getRequestLine() + " cancelled");
                }
                
                private FutureCallback<HttpResponse> init(Stage passedStage){
                    innerStage = passedStage;
                    return this;
                }

            }.init(stage));
            //latch1.await();
            // =================================================================
            //           API WORK HERE
            // =================================================================
            
            
        } catch (Exception e)
        {
            // parlam na re ..
            System.out.println(e.getMessage());
        }
    }
}
