public class TemperatureConverter {

    // F to C
    public double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    // C to F
    public double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    // Checking if temperature is extreme (in C)
    public boolean isExtremeTemperature(double celsius) {
        return celsius < -40 || celsius > 50;
    }

    // K to C
    public double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }
}
