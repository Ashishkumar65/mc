public class TrainRouteSegment {
    private Station fromStation;
    private Station toStation;
    int sequence;

    public TrainRouteSegment(Station fromSattion, Station toStation, int sequence) {
        this.fromStation = fromSattion;
        this.toStation = toStation;
        this.sequence = sequence;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public Station getToStation() {
        return toStation;
    }

    public int getSequence() {
        return sequence;
    }
}
