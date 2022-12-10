package ro.sd.a2.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.sd.a2.dto.SensorValuesDTO;

@Service
public class RabbitSender
{
    @Autowired
    private AmqpTemplate rabbitTemplate;

    private static final Logger log = LoggerFactory.getLogger(RabbitSender.class);

    @Value("queueValues.exchange")
    private String exchangeName;

    @Value("queueValues.routingkey")
    private String routingKeyName;

    public void send(SensorValuesDTO payloadValues)
    {
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, payloadValues);
        log.info("The sensor value: " + payloadValues + " was sent.");
    }

}
