import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Race {
    private int length;
    private String route;
    private int prizePool;
    private List<Car> participants;

    public Race(int length, String route, int prizePool) {
        this.length = length;
        this.route = route;
        this.prizePool = prizePool;
        this.participants = new ArrayList<>();
    }

    public void addParticipant(Car car) {
        participants.add(car);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(int prizePool) {
        this.prizePool = prizePool;
    }

    public List<Car> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Car> participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Race race = (Race) o;

        if (length != race.length) return false;
        if (prizePool != race.prizePool) return false;
        if (!Objects.equals(route, race.route)) return false;
        return Objects.equals(participants, race.participants);
    }

    @Override
    public int hashCode() {
        int result = length;
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + prizePool;
        result = 31 * result + (participants != null ? participants.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Race{" +
                "length=" + length +
                ", route='" + route + '\'' +
                ", prizePool=" + prizePool +
                ", participants=" + participants +
                '}';
    }
}
