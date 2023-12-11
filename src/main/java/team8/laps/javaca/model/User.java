package team8.laps.javaca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO) 
private int id; 
private String user_name; 
private String password; 
@ManyToOne
private Role role;
public User() {

} 
public User(String user_name, String password) {
this.user_name=user_name;
this.password=password;
}


public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

public String getUser_name() {
return user_name;
}

public void setUser_name(String user_name) {
this.user_name = user_name;
}

public int getId() {
return id;
}

public void setId(int id) {
this.id = id;
}

public Role getRole() {
return role;
}

public void setRole(Role role) {
this.role = role;
} 
}
//as 

