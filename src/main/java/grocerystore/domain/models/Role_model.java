package grocerystore.domain.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

/**
 * Created by raxis on 13.01.2017.
 */
public class Role_model {
    private static final Logger logger = LoggerFactory.getLogger(Role_model.class);

    private UUID id;            //первичный ключ
    private String roleName;    //наименование
    private List<User_model> userModels;//список пользователей

    public Role_model(){}

    public Role_model(UUID id, String roleName, List<User_model> userModels){
        this.id=id;
        this.roleName=roleName;
        this.userModels = userModels;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<User_model> getUserModels() {
        return userModels;
    }

    public void setUserModels(List<User_model> userModels) {
        this.userModels = userModels;
    }
}
