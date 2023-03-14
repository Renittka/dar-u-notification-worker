package kz.dar.university.notificationworker.service.kafka;

import kz.dar.university.notificationworker.feign.EmployeeClient;
import kz.dar.university.notificationworker.model.EmployeeDTO;
import kz.dar.university.notificationworker.model.TaskDTO;
import kz.dar.university.notificationworker.service.send.ISendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiverService {

    @Autowired
    private ISendService sendService;

    @Autowired
    private EmployeeClient employeeClient;

    @KafkaListener(id = "${spring.kafka.consumer.group-id}", topics = "${spring.kafka.topic}")
    public void consumeMessages(TaskDTO task) {

        log.info("Received from Kafka: {}", task);

        String executorId = task.getExecutorId();

        EmployeeDTO employee = employeeClient.getEmployeeById(executorId);

        sendService.sendSimpleMessage(employee.getEmail(), "Task Info", task.getDescription());

    }

}
