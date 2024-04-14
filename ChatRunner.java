package Homework6;

import java.util.*;

public class ChatRunner {
    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();
        User user1 = new User("Michael", chatServer);
        User user2 = new User("Jim", chatServer);
        User user3 = new User("Pam", chatServer);

        chatServer.registerUser(user1);
        chatServer.registerUser(user2);
        chatServer.registerUser(user3);

        user1.sendMessage(Arrays.asList("Jim", "Pam"), "Hi Guys!");
        user2.sendMessage(Arrays.asList("Michael"), "Hi Boss!");
        user2.sendMessage(Arrays.asList("Pam"), "Hey Pam!");
        user3.sendMessage(Arrays.asList("Michael", "Jim"), "Hey Michael and Jim!");

        System.out.println("Michael's chat history -");
        printChatHistory(user1);

        System.out.println("\nJim's chat history -");
        printChatHistory(user2);

        System.out.println("\nPam's chat history - ");
        printChatHistory(user3);

        user1.undoLastMessage();
        System.out.println("\nMichael undid the last message:");
        System.out.println("\nJim's chat history -");
        printChatHistory(user2);

        user3.blockUser("Michael");
        user2.sendMessage(Arrays.asList("Michael"), "Blocked Message.");
        System.out.println("\nJim sent a message to Michael but it was blocked by Pam.");
        System.out.println("Michael's chat history -");
        printChatHistory(user1);
    }

    public static void printChatHistory(User user) {
        for (Iterator iterator = user.iterator(user); iterator.hasNext(); ) {
            Message message = (Message) iterator.next();
            System.out.println(message.getSender() + ": " + message.getContent());
        }
    }
}