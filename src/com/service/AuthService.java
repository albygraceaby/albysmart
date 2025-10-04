package com.smartspend.service;

import java.io.*;
import com.smartspend.model.User;

public class AuthService {
    private final String filePath = "data/users.txt";

    public boolean login(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void register(String username, String password) {
        try (FileWriter fw = new FileWriter(filePath, true)) {
            fw.write(username + "," + password + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
