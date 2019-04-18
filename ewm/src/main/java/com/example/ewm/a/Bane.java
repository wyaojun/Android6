package com.example.ewm.a;

public class Bane {
  String Name ;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Bane{" +
                "Name='" + Name + '\'' +
                '}';
    }
}
