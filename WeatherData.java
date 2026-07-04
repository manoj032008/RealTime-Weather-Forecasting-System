public class WeatherData {

    private String city;
    private double temperature;
    private int humidity;
    private double windSpeed;
    private String condition;

    public WeatherData(String city, double temperature, int humidity, double windSpeed, String condition) {
        this.city = city;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.condition = condition;
    }

    public String getCity() {
        return city;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getCondition() {
        return condition;
    }
}