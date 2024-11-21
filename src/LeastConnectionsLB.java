import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LeastConnectionsLB {

    private Map<String, Integer> serverConnections = new HashMap<>();

    public LeastConnectionsLB(List<String> servers) {
        for (String server : servers) {
            serverConnections.put(server, 0); // 초기 연결 수는 0
        }
    }

    public String getNextServer() {
        Optional<Map.Entry<String, Integer>> entry = serverConnections.entrySet().stream()
                .min(Map.Entry.comparingByValue());

        // 연결 수가 가장 적은 서버 반환
        return entry.map(Map.Entry::getKey).orElse(null);
    }

    public void connectToServer(String server) {
        serverConnections.put(server, serverConnections.get(server) + 1);
    }

    public void disconnectFromServer(String server) {
        serverConnections.put(server, serverConnections.get(server) - 1);
    }

    public static void main(String[] args) {
        List<String> servers = List.of("Server1", "Server2", "Server3");
        LeastConnectionsLB lb = new LeastConnectionsLB(servers);

        for (int i = 0; i < 10; i++) {
            String server = lb.getNextServer();
            System.out.println("Redirecting to: " + server);
            lb.connectToServer(server); // 연결 수 증가
        }
    }

}
