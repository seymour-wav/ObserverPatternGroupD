import javax.swing.*;
import java.util.*;

class WeatherSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private double temperature = 72;
    private double humidity = 50;
    private Timer timer;

    public WeatherSubject() {
        timer = new Timer(2000, e -> randomizeWeather());
        timer.start();
    }

    private void randomizeWeather() {
        temperature += (Math.random() - 0.5) * 2;
        humidity += (Math.random() - 0.5) * 2;
        notifyObservers();
    }

    public double getTemperature() { return temperature; }
    public double getHumidity() { return humidity; }

    public void registerObserver(Observer o) { observers.add(o); }
    public void removeObserver(Observer o) { observers.remove(o); }
    public void notifyObservers() {
        Map<String, Double> diff = Map.of("temp", temperature, "humidity", humidity);
        for (Observer o : observers) o.update(diff);
    }
}
