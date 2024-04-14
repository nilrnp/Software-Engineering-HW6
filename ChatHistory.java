package Homework6;

import java.util.*;

class ChatHistory implements IterableByUser {
    private List<Message> messages;
    private Stack<MessageMemento> mementoStack;

    public ChatHistory() {
        messages = new ArrayList<>();
        mementoStack = new Stack<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
        saveMemento(message);
    }

    public void undoLastMessage() {
        if (!messages.isEmpty()) {
            Message lastMessage = messages.remove(messages.size() - 1);
            if (!mementoStack.isEmpty()) {
                mementoStack.pop();
            }
        }
    }

    private void saveMemento(Message message) {
        mementoStack.push(new MessageMemento(message.getContent(), message.getTimestamp()));
    }

    @Override
    public Iterator iterator(User userToSearchWith) {
        return new SearchMessagesByUserIterator(userToSearchWith);
    }

    private class SearchMessagesByUserIterator implements Iterator {
        private Iterator<Message> iterator;
        private User userToSearchWith;

        public SearchMessagesByUserIterator(User userToSearchWith) {
            this.userToSearchWith = userToSearchWith;
            this.iterator = messages.iterator();
        }

        @Override
        public boolean hasNext() {
            while (iterator.hasNext()) {
                Message message = iterator.next();
                if (message.getSender().equals(userToSearchWith.getName()) ||
                        message.getRecipients().contains(userToSearchWith.getName())) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Object next() {
            while (iterator.hasNext()) {
                Message message = iterator.next();
                if (message.getSender().equals(userToSearchWith.getName()) ||
                        message.getRecipients().contains(userToSearchWith.getName())) {
                    return message;
                }
            }
            return null;
        }
    }


}
