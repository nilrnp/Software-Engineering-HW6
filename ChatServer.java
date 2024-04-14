package Homework6;

import java.util.*;

class ChatServer {
    private Map<String, User> users;
    private Map<User, List<String>> blockedUsers;

    public ChatServer() {
        users = new HashMap<>();
        blockedUsers = new HashMap<>();
    }

    public void registerUser(User user) {
        users.put(user.getName(), user);
    }

    public void unregisterUser(User user) {
        users.remove(user.getName());
    }

    public void sendMessage(User sender, List<String> recipients, String content) {
        Message message = new Message(sender.getName(), recipients, content);
        for (String recipient : recipients) {
            if (!isBlocked(sender, recipient)) {
                User user = users.get(recipient);
                if (user != null) {
                    user.receiveMessage(message);
                }
            }
        }
    }

    public void blockUser(User blocker, String userName) {
        User userToBlock = users.get(userName);
        if (userToBlock != null) {
            blockedUsers.computeIfAbsent(blocker, u -> new ArrayList<>()).add(userName);
        }
    }

    private boolean isBlocked(User sender, String recipient) {
        List<String> blocked = blockedUsers.get(sender);
        return blocked != null && blocked.contains(recipient);
    }
}