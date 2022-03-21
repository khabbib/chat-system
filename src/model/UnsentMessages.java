package model;

import java.util.ArrayList;
import java.util.HashMap;

class UnsendMessages {
    private HashMap<User,ArrayList<Message>> unsend = new HashMap<>();

    // egna tillägg
    public synchronized void put(User user, Message message) {
        // hämta ArrayList – om null skapa en och placera i unsend
        // lägga till Message i ArrayList
        if(get(user) == null) {
            unsend.put(user, new ArrayList<>());
        }
        get(user).add(message);
    }
    public synchronized ArrayList<Message> get(User user) {
        return unsend.get(user);
    }

    // fler synchronized-metoder som behövs
    public synchronized void clear() {
        unsend.clear();
    }
}
