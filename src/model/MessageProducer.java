package model;

public interface MessageProducer {
    User user();
    Message message();
    String timeWhenMessageIsSent();
    String timeWhenMessageIsReceived();

    default void details() {
        System.out.println(
                "[User = " + user() + ", " +
                "Message = " + message() + "," +
                "Time when message is sent = " + timeWhenMessageIsSent() + "," +
                "Time when message is received = " + timeWhenMessageIsReceived() + "]"
        );
    }
}
