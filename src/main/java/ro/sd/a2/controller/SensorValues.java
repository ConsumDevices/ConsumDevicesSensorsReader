package ro.sd.a2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ro.sd.a2.config.RabbitSender;
import ro.sd.a2.dto.SensorValuesDTO;
import ro.sd.a2.service.ReadCSVService;

@Controller
public class SensorValues {

    private static final Logger log = LoggerFactory.getLogger(SensorValues.class);

    @Autowired
    private ReadCSVService readCSVService;

    @Autowired
    private RabbitSender rabbitSender;


    //@GetMapping("/testare")
    //@Scheduled(cron = "0 */10 * ? * *")
    //@Scheduled(cron = "*/10 * * ? * *")
    @Scheduled(cron = "*/10 * * ? * *")
    public void sendValues(){
        SensorValuesDTO sensorValuesDTO = readCSVService.getValueFromCSV();

        rabbitSender.send(sensorValuesDTO);
        log.info(sensorValuesDTO.toString());
        log.info("Sensor values were sent to the queue");
        //return new ResponseEntity<>(sensorValuesDTO, HttpStatus.OK);

    }
}

