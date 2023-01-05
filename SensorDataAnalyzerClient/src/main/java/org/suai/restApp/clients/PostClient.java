package org.suai.restApp.clients;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class PostClient {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя сенсора: ");
        String sensorName = scanner.nextLine();

        registerSensor(sensorName);

        Random random = new Random();

        float minTemperature = -100;
        float maxTemperature = 100;
        for(int i = 0; i < 10; i++){
            System.out.println(i);
            sendMeasurement((minTemperature + (random.nextDouble() * (maxTemperature - minTemperature))),
                    random.nextBoolean(), sensorName);
        }
    }

    private static void registerSensor(String sensorName){
        String url = "http://localhost:8081/sensors/registration";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", sensorName);

        makePostRequest(url, jsonData);
    }

    private static void sendMeasurement(double value, boolean raining, String sensorName){
        String url = "http://localhost:8081/measurements/add";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("value", value);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", sensorName));

        makePostRequest(url, jsonData);

    }

    private static void makePostRequest(String url, Map<String, Object> jsonData){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

        try{
            restTemplate.postForObject(url, request, String.class);
            System.out.println("Изменение применено");
        }
        catch (HttpClientErrorException e){
            System.out.println("Ошибка при отправке запроса");
            System.out.println(e.getMessage());
        }
    }


}
