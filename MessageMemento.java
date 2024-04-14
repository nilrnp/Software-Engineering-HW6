package Homework6;

import java.util.*;

class MessageMemento {
    private String content;
    private Date timestamp;

    public MessageMemento(String content, Date timestamp) {
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}