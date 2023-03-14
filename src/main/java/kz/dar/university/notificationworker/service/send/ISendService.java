package kz.dar.university.notificationworker.service.send;

public interface ISendService {

    void sendSimpleMessage(String to, String subject, String text);

}
