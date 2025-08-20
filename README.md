# 🌦️ Weather Application (Java)

A Java console-based Weather Application that fetches live weather data using [OpenWeatherMap API](https://openweathermap.org/api).

## 🚀 Features
- Get weather details by city name  
- Temperature, humidity, pressure, wind speed, visibility  
- Uses **GSON** for JSON parsing  
- Clean and user-friendly console UI  

## 🛠️ Tech Stack
- **Java 17+**
- **OpenWeatherMap API**
- **GSON Library**

## ▶️ Run Instructions
```bash
# Compile
javac -cp lib/gson-2.11.0.jar -d bin src/com/weatherapp/main/*.java src/com/weatherapp/service/*.java src/com/weatherapp/model/*.java

# Run
java -cp "bin;lib/gson-2.11.0.jar" com.weatherapp.main.WeatherApp
