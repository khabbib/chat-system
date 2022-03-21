package model;

import controller.Server;

import java.util.ArrayList;
import java.util.HashMap;

public class Unsend {
    private HashMap<String, ArrayList<Message>> list = new HashMap<>();

    // egna tillägg
    public synchronized void put(String user, Message message) {
        ArrayList<Message> messageArrayList = new ArrayList<>();

        if(list.get(user) != null){
            messageArrayList = list.get(user);
        }
        messageArrayList.add(messageArrayList.size(),message);
        list.put(user,messageArrayList);
        System.out.println(message.getText() + ": here is the text from hashmap");
    }

    public synchronized ArrayList<Message> get(String user) {
        return list.get(user);
    }

    // fler synchronized-metoder som behövs
    public synchronized void clear(User user) {
        list.get(user.getUserName()).clear();
    }
}

