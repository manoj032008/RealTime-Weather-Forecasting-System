public class Main {

    public static void main(String[] args) {
           new WeatherGUI();

        WeatherService service = new WeatherService();

        WeatherData data = service.getWeather("London");

        if (data != null) {

            System.out.println("City: " + data.getCity());
            System.out.println("Temperature: " + data.getTemperature() + " °C");
            System.out.println("Humidity: " + data.getHumidity() + " %");
            System.out.println("Wind Speed: " + data.getWindSpeed() + " m/s");
            System.out.println("Condition: " + data.getCondition());

        } else {

            System.out.println("Failed to fetch weather data.");

        }
    }
}