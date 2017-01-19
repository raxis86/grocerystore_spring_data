package grocerystore.domain.entityes;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Created by raxis on 27.12.2016.
 * Заказ
 */
@Entity
@Table(name = "orders", schema = "groceriesstore")
public class Order implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Order.class);

    private UUID id;            //первичный ключ
    private UUID userid;        //код клиента
    private UUID orderstatusid; //код статуса
    private UUID grocerylistid; //код списка заказанных продуктов
    private BigDecimal price;   //общая цена заказа
    private Date datetime;      //дата и время заказа
    private String address;     //адрес доставки

    @Id
    @Type(type="uuid-char")
    @Column(name = "ID", nullable = false, length = 36, columnDefinition = "char(36)")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Type(type="uuid-char")
    @Column(name = "USERID", nullable = false, length = 36)
    public UUID getUserid() {
        return userid;
    }

    public void setUserid(UUID userid) {
        this.userid = userid;
    }

    @Type(type="uuid-char")
    @Column(name = "STATUSID", nullable = false, length = 36)
    public UUID getOrderstatusid() {
        return orderstatusid;
    }

    public void setOrderstatusid(UUID orderstatusid) {
        this.orderstatusid = orderstatusid;
    }

    @Type(type="uuid-char")
    @Column(name = "GROCERYLISTID", nullable = false, length = 36)
    public UUID getGrocerylistid() {
        return grocerylistid;
    }

    public void setGrocerylistid(UUID grocerylistid) {
        this.grocerylistid = grocerylistid;
    }

    @Column(name = "PRICE", nullable = true, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "DATETIME", nullable = true)
    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Column(name = "ADDRESS", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
