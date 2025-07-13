
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class StaticHandler implements HttpHandler {
    private final String rootDir;
    private static final Map<String,String> MIME = new HashMap<>();
    static {
        MIME.put("html","text/html; charset=UTF-8");
        MIME.put("js",  "application/javascript");
        MIME.put("css", "text/css");
    }

    public StaticHandler(String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public void handle(HttpExchange ex) throws IOException {
        String uri = ex.getRequestURI().getPath();
        if (uri.equals("/")) uri = "/index.html";
        Path path = Paths.get(rootDir + uri).normalize();

        if (!path.startsWith(Paths.get(rootDir)) || !Files.exists(path)) {
            ex.sendResponseHeaders(404, -1);
            return;
        }

        String ext = "";
        int i = path.toString().lastIndexOf('.');
        if (i > 0) ext = path.toString().substring(i+1);
        String ct = MIME.getOrDefault(ext, "application/octet-stream");

        byte[] bs = Files.readAllBytes(path);
        ex.getResponseHeaders().set("Content-Type", ct);
        ex.sendResponseHeaders(200, bs.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(bs);
        }
    }
}
