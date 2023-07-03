package conversor_de_moedas_java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class QuotationApi {

    private static final String BASE_URL = "https://economia.awesomeapi.com.br/json/last/";

    public static double getQuotation(String from, String to) throws Exception {
        double quotation = 0.0;

        HttpURLConnection connection = null;
        try {
            URL url = new URL(BASE_URL + from + "-" + to);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = readResponse(connection);
                quotation = extractQuotationFromJson(response, from, to);
            } else {
                throw new RuntimeException("Failed to fetch quotation. Response code: " + responseCode);
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

        return quotation;
    }

    private static double extractQuotationFromJson(String json, String from, String to) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode data = mapper.readTree(json);
            String quotationKey = from + to;
            return data.get(quotationKey).get("bid").asDouble();
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract quotation from JSON.");
        }
    }

    private static String readResponse(HttpURLConnection connection) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read response. " + e.getMessage());
        }
    }
}
