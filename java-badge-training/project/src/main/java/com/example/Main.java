package com.example;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        List<String> urls = new ArrayList<String>();

        String normalUrl = "http://dnd5eapi.co/api/spells/1";
        String badUrl = "http://thisIsFake.logCom"; // to cause an error
        String nullUrl = null;

        Collections.addAll(urls, normalUrl, badUrl, nullUrl);
        Main main = new Main();

        for(String url : urls) {
            main.warningUrl(url);
            try {
                DndReponse dnd = main.sendGet(url);
                logger.info("Info: " + url + " returned " + dnd.toString());
            } catch (Exception e) {
                logger.error("Something went wrong with the web call: " + e);
            }
        }
    }

    private void warningUrl(String url) {
        if(url == null) {
            logger.fatal("The url '" + url +  "' will throw a null pointer error");
        } else if (!url.contains(".org") && !url.contains(".co") && !url.contains(".com") ) {
            logger.warn("'" + url + "' may not be a valid url");
        } else {
            logger.info("'" + url + "' is most likely a valid url");
        }
    }

    private DndReponse sendGet(String url) {
        StringBuilder textView = new StringBuilder();
        DndReponse dndReponse = new DndReponse();
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        try{
            HttpResponse response = client.execute(request);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                textView.append(line);
            }
            Gson gson = new Gson();
            Map<String, Object> map = gson.fromJson(textView.toString(), Map.class);
            dndReponse = new DndReponse(map);
        } catch (Exception e){
            logger.error("Something went wrong with the GET call: " + e);
        }
        return dndReponse;
    }
}