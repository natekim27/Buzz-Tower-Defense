package com.example.towerdefense;

public class Config {

    public static boolean isValidName(String name) {
        if (name.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public static boolean isValidConfig(String name, String difficulty) {
        return isValidName(name) && difficulty != null;
    }
}
