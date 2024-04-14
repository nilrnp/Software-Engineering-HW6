package Homework6;

import java.util.*;

class User implements IterableByUser {
    private String name;
    private ChatServer chatServer;
    private ChatHistory chatHistory;

    public User(String name, ChatServer chatServer) {
        this.name = name;
        this.chatServer = chatServer;
        this.chatHistory = new ChatHistory();
    }

    public void sendMessage(List<String> recipients, String content) {
        chatServer.sendMessage(this, recipients, content);
    }

    public void receiveMessage(Message message) {
        chatHistory.addMessage(message);
    }

    public void undoLastMessage() {
        chatHistory.undoLastMessage();
    }

    public void blockUser(String userName) {
        chatServer.blockUser(this, userName);
    }

    public ChatHistory getChatHistory() {
        return chatHistory;
    }

    @Override
    public Iterator iterator(User userToSearchWith) {
        return chatHistory.iterator(userToSearchWith);
    }

    public String getName() {
        return name;
    }
}
