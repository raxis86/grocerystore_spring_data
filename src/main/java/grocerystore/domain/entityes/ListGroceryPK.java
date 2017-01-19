package grocerystore.domain.entityes;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by raxis on 19.01.2017.
 */
public class ListGroceryPK implements Serializable {
    private UUID id;
    private UUID groceryid;

    @Column(name = "ID")
    @Type(type="uuid-char")
    @Id
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "GROCERYID")
    @Type(type="uuid-char")
    @Id
    public UUID getGroceryid() {
        return groceryid;
    }

    public void setGroceryid(UUID groceryid) {
        this.groceryid = groceryid;
    }

}
