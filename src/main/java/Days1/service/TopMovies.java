package Days1.service;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class TopMovies {
    public static void main(String[] args) throws Exception{
        String apiKey = "API-KEY";
        HttpClient client = HttpClient.newHttpClient();
        int totalFilmes = 0;
        int page = 1;
        int limite = 250;
        System.out.println("Melhores 250 filmes Segundo o TMDB");
        while(totalFilmes < limite && page <= 13) {
            String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + apiKey + "&language=pt-BR&page=" +page;



            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray results = json.getAsJsonArray("results");


            for (int i = 0; i < results.size() && totalFilmes < limite; i++) {
                JsonObject movie = results.get(i).getAsJsonObject();
                String title = movie.get("title").getAsString();
                double rating = movie.get("vote_average").getAsDouble();
                String Date = movie.get("release_date").getAsString();

                totalFilmes++;
                System.out.println((totalFilmes) + " . " + title + " (" + Date + " ) - Nota: " + rating);
            }

            page++;
        }
    }
}
