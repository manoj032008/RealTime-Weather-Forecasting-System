import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherService {

    private static final String API_KEY = "4a0f4d4a6ed67ca513d938093b85a32c";

    public WeatherData getWeather(String city) {

        String apiUrl =
                "https://api.openweathermap.org/data/2.5/weather?q="
                + city
                + "&appid="
                + API_KEY
                + "&units=metric";

        StringBuilder response = new StringBuilder();

        try {

            URL url = new URL(apiUrl);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            // Parse JSON
            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();

            String cityName = json.get("name").getAsString();

            JsonObject main = json.getAsJsonObject("main");
            double temperature = main.get("temp").getAsDouble();
            int humidity = main.get("humidity").getAsInt();

            JsonObject wind = json.getAsJsonObject("wind");
            double windSpeed = wind.get("speed").getAsDouble();

            String condition = json.getAsJsonArray("weather")
                    .get(0)
                    .getAsJsonObject()
                    .get("main")
                    .getAsString();

            return new WeatherData(
                    cityName,
                    temperature,
                    humidity,
                    windSpeed,
                    condition);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}