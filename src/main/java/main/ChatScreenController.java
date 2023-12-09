package main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;


public class ChatScreenController {
    @FXML
    private Label userNameLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private Button audioCallBtn;
    @FXML
    private Button videoCallBtn;
    @FXML
    private ListView<Message> chatList;
    @FXML
    private Button imgUploadBtn;
    @FXML
    private TextArea inputTextArea;
    @FXML
    private Button sendBtn;
    @FXML
    private Button voiceChatBtn;

    private int secondsElapsed;
    private Timeline timeline;

    public void executeSend() {
        if (timeline != null) timeline.stop();
        String message = inputTextArea.getText(); // Lấy nội dung từ TextArea
        Message userMessage = new Message(message, true);
        chatList.getItems().add(userMessage); // Thêm tin nhắn người dùng vào ListView
        chatList.scrollTo(chatList.getItems().size());

        inputTextArea.clear(); // Xóa nội dung trong TextArea
        autoChat();
    }

    public void run() {
        chatList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Message item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    Label messageLabel = new Label(item.getContent());
                    messageLabel.getStyleClass().add("message");
                    setGraphic(messageLabel);

                    if (item.isUserMessage()) {
                        getStyleClass().add("right-message");
                    } else {
                        getStyleClass().add("left-message");
                    }
                }
            }
        });

        inputTextArea.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (timeline != null) timeline.stop();
                String message = inputTextArea.getText(); // Lấy nội dung từ TextArea
                Message userMessage = new Message(message, true);
                chatList.getItems().add(userMessage); // Thêm tin nhắn người dùng vào ListView
                chatList.scrollTo(chatList.getItems().size());

                inputTextArea.clear(); // Xóa nội dung trong TextArea
                event.consume(); // Ngăn không cho ký tự enter được viết vào TextArea
                autoChat();
            }
        });
    }

    public void executeAudioCall() {

    }

    public void executeVideoCall() {

    }

    public void executeImgUpload() {

    }

    public void executeVoiceChat() {

    }

    public void autoChat() {
        AtomicInteger botMessageCount = new AtomicInteger();

        timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            // Gửi tin nhắn tự động từ "bot"
            String message = "This a sample message from another person";
            Message userMessage = new Message(message, false);
            chatList.getItems().add(userMessage);

            chatList.scrollTo(chatList.getItems().size());

            botMessageCount.getAndIncrement();

            if (botMessageCount.get() >= 3) {
                timeline.stop();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}