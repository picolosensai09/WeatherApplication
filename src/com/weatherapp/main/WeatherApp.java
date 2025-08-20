package com.weatherapp.main;

import com.weatherapp.service.WeatherService;
import com.weatherapp.model.Weather;
import java.util.Scanner;

public class WeatherApp {
    private static final WeatherService weatherService = new WeatherService();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          Weather Application         ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();
        
        while (true) {
            try {
                System.out.print("Enter city name (or 'quit' to exit): ");
                String cityName = scanner.nextLine().trim();
                
                if (cityName.equalsIgnoreCase("quit")) {
                    System.out.println("Thank you for using Weather Application!");
                    break;
                }
                
                if (cityName.isEmpty()) {
                    System.out.println("❌ City name cannot be empty. Please try again.\n");
                    continue;
                }
                
                System.out.println("🔄 Fetching weather data for " + cityName + "...\n");
                
                Weather weather = weatherService.getWeatherData(cityName);
                
                displayWeatherInfo(weather);
                
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage() + "\n");
            }
            
            System.out.println("─".repeat(50) + "\n");
        }
        
        scanner.close();
    }
    
    private static void displayWeatherInfo(Weather weather) {
        System.out.println("🌤️  Weather Information");
        System.out.println("━".repeat(30));
        System.out.printf("📍 Location: %s, %s%n", weather.getCityName(), weather.getCountry());
        System.out.printf("🌡️  Temperature: %.1f°C (Feels like %.1f°C)%n", 
                         weather.getTemperature(), weather.getFeelsLike());
        System.out.printf("💧 Humidity: %d%%%n", weather.getHumidity());
        System.out.printf("🌪️  Pressure: %d hPa%n", weather.getPressure());
        System.out.printf("☁️  Condition: %s%n", weather.getDescription());
        System.out.printf("💨 Wind Speed: %.1f m/s%n", weather.getWindSpeed());
        System.out.printf("👁️  Visibility: %.1f km%n", weather.getVisibility() / 1000.0);
        System.out.println();
    }
}
