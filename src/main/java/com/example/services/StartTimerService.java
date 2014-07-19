package com.example.services;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Path("/startTimer")
public class StartTimerService {

    @POST
    @Path("{username}")
    public Response post(@PathParam("username") String username) {
        final HttpClient httpclient = HttpClients.createDefault();
        final HttpPost httppost = new HttpPost("http://api.justyo.co/yo/");

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<NameValuePair>(2);
        params.add(new BasicNameValuePair("api_token", "095be630-8acb-2419-c32c-31bc32a79025"));
        params.add(new BasicNameValuePair("username", username));

        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Thread yoer = new Thread(new Runnable() {
            public void run() {
                sendYo(httpclient, httppost, 25);
                sendYo(httpclient, httppost, 5);
            }
        });
        yoer.start();

        return Response.ok().build();
    }

    private void sendYo(HttpClient httpclient, HttpPost httppost, int wait) {
        try {
            Thread.sleep(wait * 1000 * 60);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //Execute and get the response.
        try {
            HttpResponse response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

