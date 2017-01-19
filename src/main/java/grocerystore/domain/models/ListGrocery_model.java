package grocerystore.domain.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by raxis on 27.12.2016.
 * для таблицы заказанных продуктов
 */
public class ListGrocery_model implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(ListGrocery_model.class);

    private UUID id;        //ключ
    private UUID groceryId; //ключ продукта
    private int quantity;   //количество

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(UUID groceryId) {
        this.groceryId = groceryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
