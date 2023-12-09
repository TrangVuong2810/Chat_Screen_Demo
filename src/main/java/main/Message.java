package main;

public class Message {
    private String content;
    private boolean isUserMessage;

    public Message(String content, boolean isUserMessage) {
        this.content = content;
        this.isUserMessage = isUserMessage;
    }

    public String getContent() {
        return content;
    }

    public boolean isUserMessage() {
        return isUserMessage;
    }
}
