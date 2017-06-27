package com.example.cy.nicebins;
/**
 * Created by BogeLiu on 19/06/2017.
 */

import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONArray;
import org.json.JSONObject;


public class PostUtils {
    public static String LOGIN_URL = "http://10.0.2.2:5000/applogin";
    public static String REGISTER_URL = "http://10.0.2.2:5000/appregister";
    public static String COMMENT_URL = "http://10.0.2.2:5000/appcommentsubmit";
    public static Bundle LoginByPost(String username,String password)
    {
        String msg = "";
        Bundle resp = new Bundle();
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(LOGIN_URL).openConnection();

            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setUseCaches(false);

            JSONObject data = new JSONObject();
            data.put("username",new String(username.getBytes(), "UTF-8"));
            data.put("password",new String(password.getBytes(), "UTF-8"));
            System.out.println(data.toString());


            OutputStream out = conn.getOutputStream();
            out.write(data.toString().getBytes());
            out.flush();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                msg = new String(message.toByteArray());
                resp.putString("content",msg);
                return resp;
            }
            else{
                return resp;
            }
        }catch(Exception e){e.printStackTrace();}
        return resp;
    }

    public static String RegisterByPost(String name, String username, String email, String password, String confirm, String voicecode)
    {
        String msg = "";
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(REGISTER_URL).openConnection();

            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setUseCaches(false);
            JSONObject data = new JSONObject();
            data.put("name",new String(name.getBytes(), "UTF-8"));
            data.put("username",new String(username.getBytes(), "UTF-8"));
            data.put("email",new String(email.getBytes(), "UTF-8"));
            data.put("password",new String(password.getBytes(), "UTF-8"));
            data.put("confirm",new String (confirm.getBytes(), "UTF-8"));
            data.put("voicecode",new String (voicecode.getBytes(), "UTF-8"));
            System.out.println(data.toString());


            OutputStream out = conn.getOutputStream();
            out.write(data.toString().getBytes());
            out.flush();
            int a = conn.getResponseCode();
            if (a == 200){
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    message.write(buffer, 0, len);
                }

                is.close();
                message.close();

                msg = new String(message.toByteArray());
                return msg;
            }
            else{
                return "fail";
            }
        }catch(Exception e){e.printStackTrace();}
        return msg;
    }

    public static Bundle SubmitCommentByPost(String username,String comment)
    {
        String msg = "";
        Bundle resp = new Bundle();
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(COMMENT_URL).openConnection();

            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setUseCaches(false);

            JSONObject data = new JSONObject();
            data.put("username",new String(username.getBytes(), "UTF-8"));
            data.put("comment",new String(comment.getBytes(), "UTF-8"));
            System.out.println(data.toString());


            OutputStream out = conn.getOutputStream();
            out.write(data.toString().getBytes());
            out.flush();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream message = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    message.write(buffer, 0, len);
                }
                is.close();
                message.close();
                msg = new String(message.toByteArray());
                resp.putString("content",msg);
                return resp;
            }
            else{
                return resp;
            }
        }catch(Exception e){e.printStackTrace();}
        return resp;
    }
}
