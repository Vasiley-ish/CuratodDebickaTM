package com.example.curatoddebickatm;

public class Curator {
    public String id, name, email, password;

    public Curator() {

    }
    public Curator(String id,String email, String password, String name){
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;

    }
}
