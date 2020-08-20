import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.util.Date;

@Configuration
public class MainConfiguration {

    @Bean
    public WeatherService weatherService() {
        return new WeatherService(weatherRepository());
    }

    @Bean
    public WeatherRepository weatherRepository() {
        return new WeatherRepository();
    }

    @Bean
    @Lazy
    public WeatherManager weatherManager() {
        return new WeatherManager(weatherService());
    }

    @Bean
    public WeatherInfoIdGenerator weatherInfoIdGenerator() {
        return new WeatherInfoIdGenerator();
    }

    @Bean
    @Scope("prototype")
    public WeatherDetails weatherDetails() {
        WeatherDetails weatherDetails = new WeatherDetails();
        weatherDetails.setTimestamp(new Date());
        weatherDetails.setWeatherId(weatherInfoIdGenerator().getNextIdForWeatherDetails());
        return weatherDetails;
    }

    @Bean
    @Scope("prototype")
    public ForecastDetails forecastDetails() {
        ForecastDetails forecastDetails = new ForecastDetails();
        forecastDetails.setId(weatherInfoIdGenerator().getNextIdForForeCastDetails());
        forecastDetails.setTimeStamp(new Date());
        return forecastDetails;
    }

    @Bean
    @Scope("prototype")
    public WeatherSummary weatherSummary() {
        WeatherSummary weatherSummary = new WeatherSummary();
        weatherSummary.setId(weatherInfoIdGenerator().getNextIdForForeCastDetails());
        weatherSummary.setTimeStamp(new Date());
        return weatherSummary;
    }
}
