import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.lang.model.element.Element;
import javax.lang.model.util.ElementScanner14;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner leitura = new Scanner (System.in);
        int opcao = 0;
        double valorParaConverter;
        String moeda1;
        String moeda2;
        while (true) { 
        
        System.out.println("1) Dolar ==> Peso Argentino");
        System.out.println("2) Peso Argentino ==> Dolar");
        System.out.println("3) Dolar ==> Real Brasileiro");
        System.out.println("4) Real Brasileiro ==> Dolar");
        System.out.println("5) Dolar ==> Peso Colombiano");
        System.out.println("6) Peso Colombiano ==> Dolar");
        System.out.println("7) Sair");

        System.out.println("\nDigite uma opcao valida: ");
        opcao = leitura.nextInt();
        if(opcao == 7){
            System.out.println("Encerrando aplicação...");
            break;
        } else if (opcao > 1 && opcao < 7){  
              
        System.out.println("\nDigite o valor que deseja converter: ");    
        valorParaConverter = leitura.nextDouble();
        
            Gson gson = new GsonBuilder()
                                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                                .setPrettyPrinting()
                                .create();

                switch (opcao) {
                    case 1:
                        moeda1="USD";
                        moeda2="ARS";
                    case 2:
                        moeda1="ARS";
                        moeda2="USD";
                    case 3:
                        moeda1="USD";
                        moeda2="BRL";
                    case 4:
                        moeda1="BRL";
                        moeda2="USD";
                    case 5:
                        moeda1="USD";
                        moeda2="COP";
                    case 6:
                        moeda1="COP";
                        moeda2="DOL";
                    case 7:
                        break;
                    default:
                        break;
                        //throw new AssertionError();
                }

        ConsultaMoedas consultaMoedas = new ConsultaMoedas();

        Moedas novaMoeda = consultaMoedas.buscaMoeda(opcao, valorParaConverter, "USD");
        System.out.println("****************************");
        }
        }
    }
}