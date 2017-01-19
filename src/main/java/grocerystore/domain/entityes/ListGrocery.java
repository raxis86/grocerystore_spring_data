package grocerystore.domain.entityes;

import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by raxis on 27.12.2016.
 * для таблицы заказанных продуктов
 */
@Entity
@Table(name = "grocerylist", schema = "groceriesstore")
public class ListGrocery implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(ListGrocery.class);

    private UUID id;        //ключ
    private UUID groceryId; //ключ продукта
    private int quantity;   //количество

    @Id
    @Type(type="uuid-char")
    @Column(name = "ID", nullable = false, length = 36, columnDefinition = "char(36)")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Id
    @Type(type="uuid-char")
    @Column(name = "GROCERYID", nullable = false, length = 36)
    public UUID getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(UUID groceryId) {
        this.groceryId = groceryId;
    }

    @Column(name = "QUANTITY", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
