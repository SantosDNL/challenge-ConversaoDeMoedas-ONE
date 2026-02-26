import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConsultaMoedas {

    public Moedas buscaMoeda(int opcao, Double valorParaConverter, String sigla){

        URI endereco = URI.create("https://v6.exchangerate-api.com/v6/3ebf692ebb6a8491602114fe/latest/" + sigla);

        HttpRequest request = HttpRequest.newBuilder()
                    .uri(endereco)
                    .build();

            try {
                HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

                    JsonObject jsonCompleto = JsonParser.parseString(response.body()).getAsJsonObject();
                    JsonObject taxas = jsonCompleto.getAsJsonObject("conversion_rates");
                    
                    double dolar = taxas.get("USD").getAsDouble();
                    double real = taxas.get("BRL").getAsDouble();
                    double pesoArgentino = taxas.get("ARS").getAsDouble();
                    double pesoColombiano = taxas.get("COP").getAsDouble();

                    switch (opcao) {
                        case 1:
                            System.out.println("\nValor de " + valorParaConverter + " corresponde ao valor final de =>>> " + valorParaConverter*pesoArgentino);
                            break;
                        case 2:
                            System.out.println("\nValor de " + valorParaConverter + " corresponde ao valor final de =>>> " + valorParaConverter/pesoArgentino);
                            break;
                        case 3:
                            System.out.println("\nValor de " + valorParaConverter + " corresponde ao valor final de =>>> " + valorParaConverter*real);
                            break;
                        case 4:
                            System.out.println("\nValor de " + valorParaConverter + " corresponde ao valor final de =>>> " + valorParaConverter/real);
                            break;
                        case 5:
                            System.out.println("\nValor de " + valorParaConverter + " corresponde ao valor final de =>>> " + valorParaConverter*pesoColombiano);
                            break;
                        case 6:
                            System.out.println("\nValor de " + valorParaConverter + " corresponde ao valor final de =>>> " + valorParaConverter/pesoColombiano);
                            break;
                        default:
                                throw new AssertionError();
                        }

                    return new Gson().fromJson(response.body(), Moedas.class);

            } catch (Exception e) {
                throw new RuntimeException("Não consegui obter os valores dessa Moeda");
            }
    }

}