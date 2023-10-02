package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Logger {

    private File file;

    public Logger(String fileName) {
        this.file = new File(fileName);
    }

    public void write(String message) {

        LocalDateTime timeStamp = LocalDateTime.now();

        try {
            PrintWriter writer = null;
            if (file.exists()) {
                writer = new PrintWriter(new FileOutputStream(file, true));
            } else {
                writer = new PrintWriter(file);
            }

            writer.append(timeStamp + " " + message + "\n");
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {

        }
    }
}
