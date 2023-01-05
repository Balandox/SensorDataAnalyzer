package org.suai.restApp.clients;

import org.springframework.web.client.RestTemplate;
import org.suai.restApp.responses.MeasurementResponse;

import java.util.Scanner;

public class GetClient {

    public static void main(String[] args) {

        System.out.println("1. Получить все измерения\n" + "2. Получить кол-во дождливых дней");

        System.out.println("Выберите действие: ");
        Scanner scanner = new Scanner(System.in);
        int choose = scanner.nextInt();

        if(choose == 1) {
            MeasurementResponse measurements = getAllMeasurements();
            System.out.println("Список температур с датчиков: ");
            for(int i = 0; i < measurements.getMeasurements().size(); i++)
                System.out.println(measurements.getMeasurements().get(i).getValue());
        }
        else if(choose == 2){
            System.out.println("Кол-во дождливых дней со всех датчиков: " + getRainyDays());
        }

    }

    private static MeasurementResponse getAllMeasurements(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/measurements?sensorName={name}";
        return restTemplate.getForObject(url, MeasurementResponse.class, "Balandox sensor");
    }

    private static Integer getRainyDays(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/measurements/rainyDaysCount";
        return restTemplate.getForObject(url, Integer.class);
    }

}
