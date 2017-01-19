package grocerystore.domain.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Created by raxis on 27.12.2016.
 * Заказ
 */
public class Order_model implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Order_model.class);

    private UUID id;            //первичный ключ
    private UUID userid;        //код клиента
    private UUID orderstatusid; //код статуса
    private UUID grocerylistid; //код списка заказанных продуктов
    private BigDecimal price;   //общая цена заказа
    private Date datetime;      //дата и время заказа
    private String address;     //адрес доставки

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserid() {
        return userid;
    }

    public void setUserid(UUID userid) {
        this.userid = userid;
    }

    public UUID getOrderstatusid() {
        return orderstatusid;
    }

    public void setOrderstatusid(UUID orderstatusid) {
        this.orderstatusid = orderstatusid;
    }

    public UUID getGrocerylistid() {
        return grocerylistid;
    }

    public void setGrocerylistid(UUID grocerylistid) {
        this.grocerylistid = grocerylistid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
