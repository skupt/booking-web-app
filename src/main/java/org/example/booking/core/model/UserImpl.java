package org.example.booking.core.model;

import org.example.booking.intro.model.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_impl", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class UserImpl implements User, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @OneToOne(mappedBy = "userImpl", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserAccount userAccount;
    @OneToMany(mappedBy = "user")
    private Set<TicketImpl> tickets;

    public UserImpl() {
    }

    public UserImpl(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "UserImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
