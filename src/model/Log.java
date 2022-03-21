package model;

import javax.swing.*;
import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.io.FileWriter;

/**
 * retrieves log between two times
 */
public class Log extends JFrame{

    private String info;
    private LocalDateTime thetimes;
    private JList<String> Contacts;

    // Constructor I
    public Log(String info, LocalDateTime thetimes) {
        this.info = info;
        this.thetimes = thetimes;
    }

    // Constructor II
    public Log(JList contacts, String info) {
        this.Contacts = contacts;
        this.info = info;
    }

    // Constructor III
    public Log() { }

    // Check the log
    public static Log checkLog(String start, String end) {
          List<Log> trafficinput = new ArrayList<Log>();
          Log n = null;

          try {
              Scanner s = new Scanner(new File("./log/log.txt"));
              while (s.hasNext()) {
                  String first = s.nextLine();
                  Scanner scLine = new Scanner(first).useDelimiter(" % ");
                  String inf = scLine.next();

                  LocalDateTime date = LocalDateTime.parse(scLine.next(), DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
                  trafficinput.add(new Log(inf, date));
              }

              DateTimeFormatter dtf= DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
              LocalDateTime ltmin = LocalDateTime.parse(start, dtf);
              LocalDateTime ltmax = LocalDateTime.parse(end, dtf);

              for (Log log : trafficinput) {
                  if ((log.getThetimes().isAfter(ltmin)||log.getThetimes().equals(ltmin)) && (log.getThetimes().isBefore(ltmax) ||log.getThetimes().equals(ltmax) )) {
                      outputs(log);
                      n = log;
                  }
              }
              return n;

          } catch (IOException e) {
              e.printStackTrace();
          }
          return null;
    }

    // Output
    private static void outputs(Log msg) throws IOException {
          FileWriter fw = new FileWriter("./log/outputs.txt",true);
          fw.write(String.valueOf(msg));
          fw.close();
    }

    // Log contacts
    public void logContacts(Log log, User user){
          try (FileOutputStream fos = new FileOutputStream("./contacts/"+user.getUserName()+".dat");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(log);
          } catch (IOException ex) {
                ex.printStackTrace();
          }
    }

    // Getters and setters
    public JList<String> getContacts() {
          return Contacts;
    }

    public LocalDateTime getThetimes() {
        return thetimes;
    }

    // To String
    @Override
    public String toString() {
        return  info + ", tider:" + thetimes + "\n";
    }

}