package grocerystore.domain.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by raxis on 27.12.2016.
 * Статус заказа
 */
public class OrderStatus_model implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(OrderStatus_model.class);

    private UUID id;        //первичный ключ
    private String status;  //наименование статуса

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
