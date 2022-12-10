package ro.sd.a2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import ro.sd.a2.controller.SensorValues;

@SpringBootApplication
@EnableScheduling
public class Assignment2SdApplication {

    public static void main(String[] args){
        SpringApplication.run(Assignment2SdApplication.class, args);

        //ReadCSVService readCSVService = new ReadCSVService();
        //SensorValues sensorValues = new SensorValues();
        //sensorValues.sendValues();


    }

}
