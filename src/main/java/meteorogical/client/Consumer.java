package meteorogical.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import meteorogical.client.dto.MeasurementsResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Consumer {
    public static void main(String[] args)  {


        String sensorName = "ClientSensorName";
        Random random = new Random();
        for(int i = 0; i < 500; i++) {
            sendMeasurement(random.nextDouble() * 100, random.nextBoolean(), sensorName);
        }
    }


    public static void sendMeasurement(double airTemperature, boolean raining, String sensorName) {

        String urlMeasurementAdd = "http://localhost:8080/measurement/add";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("airTemperature", airTemperature);
        jsonData.put("raining", raining);
        jsonData.put("sensor", Map.of("name", sensorName));

        postRequestWithJsonData(urlMeasurementAdd, jsonData);

    }

    public static void sensorRegister(String sensorClientName) {
        String urlSensorRegister = "http://localhost:8080/sensors/registration";

        Map<String, Object> jsonData = new HashMap<>();
        jsonData.put("name", sensorClientName);

        postRequestWithJsonData(urlSensorRegister, jsonData);
    }
    public static void postRequestWithJsonData(String url, Map<String, Object> jsonData) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request = new HttpEntity<>(jsonData, headers);

        String responseMeasurement = restTemplate.postForObject(url, request, String.class);
        System.out.println(responseMeasurement);
    }
}
