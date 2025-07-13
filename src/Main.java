

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        TicketService service = new TicketService();
        server.createContext("/ticket", new APIHandler(service));
        server.createContext("/caisse", new APIHandler(service)); // /caisse/1,2,3
        server.createContext("/etat", new APIHandler(service));

        // pour afficher l'interface HTML
        server.createContext("/", new StaticHandler("public"));

        server.setExecutor(null);
        server.start();

        System.out.println("Serveur prêt sur http://localhost:" + port);
    }
}
