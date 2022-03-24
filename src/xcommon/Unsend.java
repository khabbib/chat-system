package xcommon;

import java.util.ArrayList;
import java.util.HashMap;

public class Unsend {
    private HashMap<String, ArrayList<Message>> unsendList = new HashMap<>();

    // egna tillägg
    public synchronized void put(String user, Message message) {
        ArrayList<Message> messageArrayList = new ArrayList<>();

        if(unsendList.get(user) != null){
            messageArrayList = unsendList.get(user);
        }
        messageArrayList.add(messageArrayList.size(),message);
        unsendList.put(user,messageArrayList);
        System.out.println(message.getText() + ": here is the text from hashmap");
    }

    public synchronized ArrayList<Message> get(String user) {
        return unsendList.get(user);
    }

    // fler synchronized-metoder som behövs
    public synchronized void clear(User user) {
        unsendList.get(user.getUserName()).clear();
    }
}

