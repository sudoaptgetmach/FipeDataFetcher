package org.mach.fipedatafetcher.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ApiService {

    public String fetchData(String vehicleType, String brand, String model, String year) throws IOException {
        String urlString = "https://parallelum.com.br/fipe/api/v1/" + vehicleType + "/marcas/" + brand + "/modelos/" + model + "/anos/" + year;
        return getString(urlString);
    }

    public String listData(String urlString) throws IOException {
        return getString(urlString);
    }

    private String getString(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }

        Scanner scanner = new Scanner(url.openStream());
        StringBuilder inline = new StringBuilder();
        while (scanner.hasNext()) {
            inline.append(scanner.nextLine());
        }
        scanner.close();

        return inline.toString();
    }
}