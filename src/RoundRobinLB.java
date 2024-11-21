import java.util.List;

public class RoundRobinLB {

    private List<String> servers;
    private int currentIndex = 0;

    public RoundRobinLB(List<String> servers) {
        this.servers = servers;
    }

    public String getNextServer() {
        String server = servers.get(currentIndex);
        currentIndex = (currentIndex + 1) % servers.size(); // 순환
        return server;
    }

    public static void main(String[] args) {
        List<String> servers = List.of("Server1", "Server2", "Server3");
        RoundRobinLB lb = new RoundRobinLB(servers);

        for (int i = 0; i < 10; i++) {
            System.out.println("Redirecting to: " + lb.getNextServer());
        }
    }

}
