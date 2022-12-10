package ro.sd.a2.service;

import org.springframework.stereotype.Service;
import ro.sd.a2.dto.SensorValuesDTO;
import java.io. * ;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReadCSVService {

    private int contor = 0;

    public SensorValuesDTO getValueFromCSV()
    {
        SensorValuesDTO sensorValuesDTO = new SensorValuesDTO();
        float valoareCitita=-1;
        try {
            int contorActual=0;

            File file = new File("./sensor.csv");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = " ";
            String[] elementeLinie;
            while ((line = br.readLine()) != null && contorActual<=contor) {
                elementeLinie = line.split(",");
                for (String element: elementeLinie) {
                    //System.out.print(tempStr + " ");
                    valoareCitita = Float.valueOf(element);
                    contorActual++;
                }
                //System.out.println();
            }
            System.out.println(valoareCitita);
            contor++;
            br.close();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        sensorValuesDTO.setValue(valoareCitita);
        sensorValuesDTO.setDate(LocalDateTime.now());

        //acum citesc id device din txt
        try {
            int contorActual=0;

            File file = new File("./deviceId.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String device_id = " ";
            device_id = br.readLine();
            sensorValuesDTO.setDeviceId(UUID.fromString(device_id));
            br.close();

        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }

        return sensorValuesDTO;
    }
}
