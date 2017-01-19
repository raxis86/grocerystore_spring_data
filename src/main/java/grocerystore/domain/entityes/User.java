package grocerystore.domain.entityes;

import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * Created by raxis on 13.01.2017.
 */
@Entity
@Table(name = "users", schema = "groceriesstore")
public class User implements Serializable{
    private static final Logger logger = LoggerFactory.getLogger(User.class);

    private UUID id;            //первичный ключ
    private String email;       //email
    private String password;    //пароль
    private String status;      //статус пользователя
    private String name;        //имя
    private String lastname;    //фамилия
    private String surname;     //отчество
    private String address;     //адрес
    private String phone;       //телефон
    private List<Role> roles;//список ролей

    public User() {}

    public User(UUID id, String email, String password, String status,
                String name, String lastname, String surname,
                String address, String phone, List<Role> roles){
        this.id=id;
        this.password=password;
        this.status=status;
        this.email=email;
        this.name=name;
        this.lastname=lastname;
        this.surname=surname;
        this.address=address;
        this.phone=phone;
        this.roles=roles;
    }

    @Id
    @Type(type="uuid-char")
    @Column(name = "ID", nullable = false, length = 36, columnDefinition = "char(36)")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 60)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "LASTNAME", nullable = true, length = 100)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "SURNAME", nullable = true, length = 100)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "ADDRESS", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "PHONE", nullable = true, length = 100)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usersandroles",
            joinColumns=@JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
