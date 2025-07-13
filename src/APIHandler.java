
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import com.google.gson.Gson;

/**
 * Gère les requêtes REST :
 * - POST /ticket
 * - POST /caisse/{id}
 * - GET  /etat
 */
public class APIHandler implements HttpHandler {
    private final TicketService service;
    private final Gson gson = new Gson();

    public APIHandler(TicketService service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange ex) throws IOException {
        String method = ex.getRequestMethod();
        String path = ex.getRequestURI().getPath();
        ex.getResponseHeaders().add("Content-Type", "application/json");

        String response = "";
        int code = 200;

        if (method.equals("POST") && path.equals("/ticket")) {
            // Crée un nouveau ticket
            Ticket t = service.nouveauTicket();
            response = gson.toJson(t);
            code = 201;
        }

        else if (method.equals("POST") && path.startsWith("/caisse/")) {
            try {
                // Extraire le numéro de caisse depuis l'URL
                int caisseId = Integer.parseInt(path.split("/")[2]);
                Ticket t = service.appelerSuivant(caisseId);
                if (t == null) {
                    code = 204; // No Content
                } else {
                    response = gson.toJson(t);
                }
            } catch (Exception e) {
                code = 400;
                response = "{\"error\": \"Numéro de caisse invalide\"}";
            }
        }

        else if (method.equals("GET") && path.equals("/etat")) {
            Map<String, Object> etat = new HashMap<>();
            etat.put("enAttente", service.getFileAttente());
            etat.put("appels", service.getAppelsEnCours());
            etat.put("totalAttente", service.getNombreEnAttente());
            response = gson.toJson(etat);
        }

        else {
            code = 404;
            response = "{\"error\": \"Route inconnue\"}";
        }

        // Envoyer la réponse
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        ex.sendResponseHeaders(code, bytes.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }
}
