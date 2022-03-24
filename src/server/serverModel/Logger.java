package server.serverModel;

import xcommon.User;

import javax.swing.*;
import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.io.FileWriter;

public class Logger {

    private String info;
    private JList<String> contacts;
    private LocalDateTime localDateTime;


    // Constructor I
    public Logger(String info, LocalDateTime localDateTime) {
        this.info = info;
        this.localDateTime = localDateTime;
    }

    // Constructor II
    public Logger(JList contacts, String info) {
        this.contacts = contacts;
        this.info = info;
    }

    // Constructor III
    public Logger() { }

    // Check the log
    public static Logger checkLogger(String start, String end) {
          List<Logger> trafficinput = new ArrayList<Logger>();
          Logger n = null;

          try {
              Scanner s = new Scanner(new File("./log/log.txt"));
              while (s.hasNext()) {
                  String first = s.nextLine();
                  Scanner scLine = new Scanner(first).useDelimiter(" % ");
                  String inf = scLine.next();

                  LocalDateTime date = LocalDateTime.parse(scLine.next(), DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
                  trafficinput.add(new Logger(inf, date));
              }

              DateTimeFormatter dtf= DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
              LocalDateTime ltmin = LocalDateTime.parse(start, dtf);
              LocalDateTime ltmax = LocalDateTime.parse(end, dtf);

              for (Logger logger : trafficinput) {
                  if ((logger.getLocalDateTime().isAfter(ltmin)|| logger.getLocalDateTime().equals(ltmin)) && (logger.getLocalDateTime().isBefore(ltmax) || logger.getLocalDateTime().equals(ltmax) )) {
                      outputs(logger);
                      n = logger;
                  }
              }
              return n;

          } catch (IOException e) {
              e.printStackTrace();
          }
          return null;
    }

    // Output
    private static void outputs(Logger msg) throws IOException {
          FileWriter fw = new FileWriter("./log/outputs.txt",true);
          fw.write(String.valueOf(msg));
          fw.close();
    }

    // Log contacts
    public void logContacts(Logger logger, User user){
          try (FileOutputStream fos = new FileOutputStream("./contacts/"+user.getUserName()+".dat");
                 ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(logger);
          } catch (IOException ex) {
                ex.printStackTrace();
          }
    }

    // Getters and setters
    public JList<String> getContacts() {
          return contacts;
    }
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    // To String
    @Override
    public String toString() {
        return  info + ", tider:" + localDateTime + "\n";
    }

}