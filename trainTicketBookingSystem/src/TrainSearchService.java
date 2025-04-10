import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrainSearchService {
    List<TrainSchedule> schedules;

    public TrainSearchService(List<TrainSchedule> schedules) {
        this.schedules = schedules;
    }

    public List<TrainSchedule> searchTrains(Station from, Station to, LocalDate date) {
        List<TrainSchedule> matchingSchedules = new ArrayList<>();
        for (TrainSchedule schedule : schedules) {
            if (schedule.date.equals(date) && schedule.train.servesRoute(from, to)) {
                matchingSchedules.add(schedule);
            }
        }
        return matchingSchedules;
    }
}
