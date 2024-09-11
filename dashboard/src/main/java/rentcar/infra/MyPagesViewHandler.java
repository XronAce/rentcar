package rentcar.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import rentcar.config.kafka.KafkaProcessor;
import rentcar.domain.*;

@Service
public class MyPagesViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MyPagesRepository myPagesRepository;
    //>>> DDD / CQRS
}
