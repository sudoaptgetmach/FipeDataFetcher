package org.mach.fipedatafetcher.manager;

import org.mach.fipedatafetcher.model.Vehicle;
import org.mach.fipedatafetcher.service.ApiService;
import org.mach.fipedatafetcher.service.DataConverter;

import java.io.IOException;
import java.util.Scanner;

public class AppManager {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ApiService apiService = new ApiService();
    private static final DataConverter converter = new DataConverter();

    public static void main(String[] args) {

        System.out.println("OPÇÕES\n");
        System.out.println("Carros");
        System.out.println("Motos");
        System.out.println("Caminhões");

        String input;
        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("CARROS") || input.equalsIgnoreCase("CAMINHOES") || input.equalsIgnoreCase("MOTOS")) {

                try {
                    String vehicleType = input.equalsIgnoreCase("carros") ? "carros" : input.equalsIgnoreCase("motos") ? "motos" : "caminhoes";
                    String brand = getBrand(vehicleType);
                    String model = getModel(vehicleType, brand);
                    String year = getYear(vehicleType, brand, model);

                    var fetchdata = apiService.fetchData(vehicleType, brand, model, year);
                    Vehicle vehicle = converter.fetchData(fetchdata, Vehicle.class);
                    System.out.println("Dados do veículo: " + vehicle.toString());
                } catch (Exception e) {
                    throw new RuntimeException("Dado inválido.\n" + e.getMessage());
                }
                break;
            } else {
                System.out.println("Escolha uma das opções acima.");
            }
        }
    }

    private static String getBrand(String vehicleType) throws IOException {
        System.out.println("Qual a marca do " + vehicleType + " que você deseja buscar?");
        String brand = scanner.nextLine();

        if (brand.equalsIgnoreCase("lista")) {
            var list = apiService.listData("https://parallelum.com.br/fipe/api/v1/" + vehicleType + "/marcas/");
            System.out.println("Marcas disponíveis:");
            printList(list);
            brand = scanner.nextLine();
        }
        return brand;
    }

    private static String getModel(String vehicleType, String brand) throws IOException {
        System.out.println("Qual o modelo do " + vehicleType + " que você deseja buscar?");
        String model = scanner.nextLine();

        if (model.equalsIgnoreCase("lista")) {
            var list = apiService.listData("https://parallelum.com.br/fipe/api/v1/" + vehicleType + "/marcas/" + brand + "/modelos/");
            System.out.println("Modelos disponíveis:");
            printList(list);
            model = scanner.nextLine();
        }
        return model;
    }

    private static String getYear(String vehicleType, String brand, String model) throws IOException {
        System.out.println("Qual o ano do " + vehicleType + " que você deseja buscar?");
        String year = scanner.nextLine();

        if (year.equalsIgnoreCase("lista")) {
            var list = apiService.listData("https://parallelum.com.br/fi7pe/api/v1/" + vehicleType + "/marcas/" + brand + "/modelos/" + model + "/anos");
            System.out.println("Anos disponíveis:");
            printList(list);
            year = scanner.nextLine();
        }
        return year;
    }

    private static void printList(String list) {
        list = list.replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace("\"", "");
        String[] items = list.split(",");
        for (String item : items) {
            System.out.println(item.trim());
        }
    }
}