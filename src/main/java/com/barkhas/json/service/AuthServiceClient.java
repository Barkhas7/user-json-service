package com.barkhas.json.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AuthServiceClient {

    public boolean validateToken(String token) {
        try {
            String soapRequest =
                    "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" " +
                    "xmlns:ser=\"http://service.soap.barkhas.com/\">" +
                    "<soapenv:Header/>" +
                    "<soapenv:Body>" +
                    "<ser:validateToken>" +
                    "<token>" + token + "</token>" +
                    "</ser:validateToken>" +
                    "</soapenv:Body>" +
                    "</soapenv:Envelope>";

            URL url = new URL("http://localhost:8081/auth");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = soapRequest.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            System.out.println("SOAP validateToken response code: " + responseCode);

            BufferedReader br;
            if (responseCode >= 200 && responseCode < 300) {
                br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            } else {
                br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), StandardCharsets.UTF_8));
            }

            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            String responseXml = response.toString();
            System.out.println("SOAP validateToken response: " + responseXml);

            return responseXml.contains(">true</");

        } catch (Exception e) {
            System.out.println("SOAP validateToken call failed.");
            e.printStackTrace();
            return false;
        }
    }
}