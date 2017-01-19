package grocerystore.domain.entityes;

import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by raxis on 13.01.2017.
 */
@Entity
@Table(name = "roles", schema = "groceriesstore")
public class Role implements Serializable{
    private static final Logger logger = LoggerFactory.getLogger(Role.class);

    private UUID id;            //первичный ключ
    private String roleName;    //наименование
    private List<User> users = new ArrayList<>();//список пользователей

    public Role(){}

    public Role(UUID id, String roleName, List<User> users){
        this.id=id;
        this.roleName=roleName;
        this.users=users;
    }

    @Id
    @Type(type="uuid-char")
    @Column(name = "ID", nullable = false, length = 36)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ROLENAME", nullable = true, length = 45)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usersandroles",
            joinColumns=@JoinColumn(name = "ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
