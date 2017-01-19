package grocerystore.domain.entityes;

import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by raxis on 27.12.2016.
 * Статус заказа
 */
@Entity
@Table(name = "orderupdates", schema = "groceriesstore")
public class OrderStatus implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(OrderStatus.class);

    private UUID id;        //первичный ключ
    private String status;  //наименование статуса

    @Id
    @Type(type="uuid-char")
    @Column(name = "ID", nullable = false, length = 36)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "STATUS", nullable = false, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
