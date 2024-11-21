import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WeightedLB {

    private Map<String, Integer> serversWithWeights;
    private final List<String> weightedServers = new ArrayList<>();

    public WeightedLB(Map<String, Integer> serversWithWeights) {
        this.serversWithWeights = serversWithWeights;
        for (Map.Entry<String, Integer> entry : serversWithWeights.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                weightedServers.add(entry.getKey()); // 가중치만큼 서버 추가
            }
        }
    }

    public String getNextServer() {
        int randomIndex = new Random().nextInt(weightedServers.size());
        return weightedServers.get(randomIndex);
    }

    public static void main(String[] args) {
        Map<String, Integer> servers = Map.of(
                "Server1", 1, // 가중치 1
                "Server2", 3, // 가중치 3
                "Server3", 2  // 가중치 2
        );

        WeightedLB lb = new WeightedLB(servers);

        for (int i = 0; i < 10; i++) {
            System.out.println("Redirecting to: " + lb.getNextServer());
        }
    }

}
