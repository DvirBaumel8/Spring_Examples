import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherSummary {
    private List<WeatherDetails> weatherDetailsList;
    private long id;
    private Date timeStamp;

    public WeatherSummary() {
        this.weatherDetailsList = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(WeatherDetails details : weatherDetailsList) {
            stringBuilder.append(details.toString());
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    public List<WeatherDetails> getWeatherDetailsList() {
        return weatherDetailsList;
    }

    public void setWeatherDetailsList(List<WeatherDetails> weatherDetailsList) {
        this.weatherDetailsList = weatherDetailsList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
