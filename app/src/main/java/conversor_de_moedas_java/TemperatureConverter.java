package conversor_de_moedas_java;

import java.lang.reflect.Method;

public class TemperatureConverter {

    public double convert(String from, String to, double value) throws Exception {
        Method method = this.getClass().getDeclaredMethod(from + "To" + to, double.class);
        return (double) method.invoke(this, value);
    }

    private static double CelsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

    private static double KelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    private static double CelsiusToFahrenheit(double celsius) {
        return (celsius * 1.8) + 32;
    }

    private static double FahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) / 1.8;
    }

    private static double FahrenheitToKelvin(double fahrenheit) {
        return CelsiusToKelvin(FahrenheitToCelsius(fahrenheit));
    }

    private static double KelvinToFahrenheit(double kelvin) {
        return CelsiusToFahrenheit(KelvinToCelsius(kelvin));
    }
}
