import java.util.ArrayList;
import java.util.List;

public class Train {
    private String trainId;
    private String name;

    List<TrainRouteSegment> route;
    List<Seat> seats;
    public String getTrainId() {
        return trainId;
    }

    public String getName() {
        return name;
    }

    public List<TrainRouteSegment> getRoute() {
        return route;
    }

    public List<Seat> getSeats() {
        return seats;
    }
    public Train(String trainId, String name){
        this.trainId = trainId;
        this.name = name;
        this.route = new ArrayList<>();
        this.seats = new ArrayList<>();
    }

    public boolean servesRoute(Station from, Station to){
        int fromIndex = -1, toIndex = -1;
        for(int i =0; i < route.size();i++){
            if(route.get(i).getFromStation().equals(from)){
                fromIndex = i;
            }
            if(route.get(i).getToStation().equals(to)){
                toIndex = i;
            }
        }
        return fromIndex != -1 && toIndex != -1 && fromIndex < toIndex;
    }
}
