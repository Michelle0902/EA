package myapplication.security.userservice;

import jakarta.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private int id;
    private String role;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @Override
    public String toString() {
        return "Role{" +
                ", role='" + role + '\'' +
                '}';
    }
}
