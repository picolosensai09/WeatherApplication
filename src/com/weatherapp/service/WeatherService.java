// File: src/com/weatherapp/service/WeatherService.java
package com.weatherapp.service;

import com.google.gson.JsonObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.weatherapp.model.Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherService {
    private static final String API_KEY = "af45edf9a8e426fb34512107e4a03dad";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather";
    private static final int TIMEOUT = 5000;

    public Weather getWeatherData(String cityName) throws Exception {
        if (API_KEY.equals("YOUR_API_KEY_HERE")) {
            throw new Exception("Please set your OpenWeatherMap API key in WeatherService.java");
        }

        String encodedCityName = URLEncoder.encode(cityName, StandardCharsets.UTF_8);
        String apiUrl = String.format("%s?q=%s&appid=%s&units=metric",
                BASE_URL, encodedCityName, API_KEY);

        String jsonResponse = makeHttpRequest(apiUrl);
        return parseWeatherData(jsonResponse);
    }

    private String makeHttpRequest(String apiUrl) throws Exception {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);
            connection.setRequestProperty("User-Agent", "Weather-App/1.0");

            int responseCode = connection.getResponseCode();
            reader = new BufferedReader(new InputStreamReader(
                    responseCode == HttpURLConnection.HTTP_OK ?
                            connection.getInputStream() : connection.getErrorStream()
            ));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("API request failed with code " + responseCode + ": " + response);
            }

            return response.toString();

        } catch (IOException e) {
            throw new Exception("Network error: " + e.getMessage());
        } finally {
            if (reader != null) reader.close();
            if (connection != null) connection.disconnect();
        }
    }

    private Weather parseWeatherData(String jsonResponse) throws Exception {
        try {
        	JsonObject json = new Gson().fromJson(jsonResponse, JsonObject.class);
            Weather weather = new Weather();

            weather.setCityName(json.get("name").getAsString());
            JsonObject sys = json.getAsJsonObject("sys");
            weather.setCountry(sys.has("country") ? sys.get("country").getAsString() : "Unknown");

            JsonObject main = json.getAsJsonObject("main");
            weather.setTemperature(main.get("temp").getAsDouble());
            weather.setFeelsLike(main.get("feels_like").getAsDouble());
            weather.setHumidity(main.get("humidity").getAsInt());
            weather.setPressure(main.get("pressure").getAsInt());

            JsonArray weatherArray = json.getAsJsonArray("weather");
            if (weatherArray.size() > 0) {
                JsonObject weatherObj = weatherArray.get(0).getAsJsonObject();
                weather.setMainCondition(weatherObj.get("main").getAsString());
                weather.setDescription(capitalizeFirstLetter(weatherObj.get("description").getAsString()));
            }

            if (json.has("wind")) {
                JsonObject wind = json.getAsJsonObject("wind");
                if (wind.has("speed")) {
                    weather.setWindSpeed(wind.get("speed").getAsDouble());
                }
            }

            if (json.has("visibility")) {
                weather.setVisibility(json.get("visibility").getAsInt());
            }

            return weather;

        } catch (Exception e) {
            throw new Exception("Failed to parse weather data: " + e.getMessage());
        }
    }

    private String capitalizeFirstLetter(String text) {
        if (text == null || text.isEmpty()) return text;

        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i > 0) result.append(" ");
            String word = words[i];
            result.append(Character.toUpperCase(word.charAt(0)))
                  .append(word.substring(1).toLowerCase());
        }

        return result.toString();
    }
}
