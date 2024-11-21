import java.util.List;

public class IPHashLB {

    private List<String> servers;

    public IPHashLB(List<String> servers) {
        this.servers = servers;
    }

    public String getServer(String ipAddress) {
        int hash = ipAddress.hashCode();
        int serverIndex = Math.abs(hash) % servers.size(); // 서버 목록 인덱스로 매핑
        return servers.get(serverIndex);
    }

    public static void main(String[] args) {
        List<String> servers = List.of("Server1", "Server2", "Server3");
        IPHashLB lb = new IPHashLB(servers);

        String[] ips = {"192.168.0.1", "192.168.0.2", "192.168.0.3"};
        for (String ip : ips) {
            System.out.println("IP " + ip + " redirects to: " + lb.getServer(ip));
        }
    }

}
