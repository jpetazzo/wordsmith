import com.google.common.base.Charsets;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) throws Exception {
        Class.forName("org.postgresql.Driver");

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/noun", handler(Suppliers.memoize(() -> randomWord("nouns"))));
        server.createContext("/verb", handler(Suppliers.memoize(() -> randomWord("verbs"))));
        server.createContext("/adjective", handler(Suppliers.memoize(() -> randomWord("adjectives"))));
        server.start();
        System.out.println("Server started.");
    }

    private static String randomWord(String table) {
        String pghost = System.getenv("PGHOST");
        if (pghost == null) pghost = "db";
        String pgport = System.getenv("PGPORT");
        if (pgport == null) pgport = "5432";
        String pguser = System.getenv("PGUSER");
        if (pguser == null) pguser = "postgres";
        String pgpassword = System.getenv("PGPASSWORD");
        if (pgpassword == null) pgpassword = "";
        String url = String.format("jdbc:postgresql://%s:%s/postgres", pghost, pgport);
        try (Connection connection = DriverManager.getConnection(url, pguser, pgpassword)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet set = statement.executeQuery("SELECT word FROM " + table + " ORDER BY random() LIMIT 1")) {
                    while (set.next()) {
                        return set.getString(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new NoSuchElementException(table);
    }

    private static HttpHandler handler(Supplier<String> word) {
        return t -> {
            String response = "{\"word\":\"" + word.get() + "\"}";
            byte[] bytes = response.getBytes(Charsets.UTF_8);

            System.out.println(response);
            t.getResponseHeaders().add("content-type", "application/json; charset=utf-8");
            t.sendResponseHeaders(200, bytes.length);

            try (OutputStream os = t.getResponseBody()) {
                os.write(bytes);
            }
        };
    }
}
