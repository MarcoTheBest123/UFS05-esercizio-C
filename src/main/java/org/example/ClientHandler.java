package org.example;

        import com.sun.net.httpserver.HttpExchange;
        import com.sun.net.httpserver.HttpHandler;

        import java.io.*;
        import java.net.URI;

public class ClientHandler implements HttpHandler {

    DatabaseHandler dbh = new DatabaseHandler();

    public void handle(HttpExchange t) throws IOException {
        InputStream is = t.getRequestBody();
        URI uri = t.getRequestURI();
        String query = uri.getQuery();
        String result = getCommand(query);

        String s = read(is); // .. read the request body

        String response = "<!doctype html>\n" +
                "<html lang=en>\n" +
                "<head>\n" +
                "<meta charset=utf-8>\n" +
                "<title>MyJava Sample</title>\n" +
                "</head>\n" +
                "<body>\n" +

                "<p>Write 'help to see all the commands available.'</p>\n" +

                "</br>I'm the content" +
                "</br>\n" +
                s +

                "</br>query:" +
                "</br>\n" +
                query +
                "<h3>Risultato:</h3>\n" +
                result +
                "</body>\n" +
                "</html>\n";

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String read(InputStream is) {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(is));

        System.out.println("\n");
        String received = null;
        while (true) {
            String s = null;
            try {
                if ((s = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(s);
            received += s;
        }
        return received;
    }

    String getCommand(String query) {

        query = cleanQuery(query);
        System.out.println("cioa");
        String result = "";

        switch (query) {
            default:
                result = query + " is not a command";
                break;

            case "more_expensive":
                result = dbh.maxPrice();
                break;

            case "all":
                result = dbh.all();
                break;

            case "all_sorted":
                result = dbh.all_sorted();
                break;

        }

        return result;
    }

    String cleanQuery(String query) {

        return query.split("=")[1];
    }
}
