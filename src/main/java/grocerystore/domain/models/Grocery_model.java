package grocerystore.domain.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by raxis on 27.12.2016.
 * Класс для продуктов
 */
public class Grocery_model implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Grocery_model.class);

    private UUID id;            //PK
    private UUID parentid;      //ключ родителя (для иерархии каталогов)
    private boolean iscategory; //флаг, что это каталог или продукт
    private String name;        //наименование
    private int quantity;       //количество
    private BigDecimal price;   //цена

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getParentid() {
        return parentid;
    }

    public void setParentid(UUID parentid) {
        this.parentid = parentid;
    }

    public boolean isIscategory() {
        return iscategory;
    }

    public void setIscategory(boolean iscategory) {
        this.iscategory = iscategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result=prime*result+(int)id.getLeastSignificantBits();
        result=prime*result+(int)parentid.getMostSignificantBits();
        int i=iscategory==true ? 1 : 0;
        result=prime*result+i;
        result=prime*result+name.hashCode();
        result=prime*result+quantity;
        result=prime*result+price.toBigInteger().intValue();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Grocery_model other = (Grocery_model) obj;
        if (!id.equals(other.id))
            return false;
        if (!parentid.equals(other.parentid))
            return false;
        if (iscategory != other.iscategory)
            return false;
        if (!name.equals(other.name))
            return false;
        if (quantity != other.quantity)
            return false;
        if (!price.equals(other.price))
            return false;
        return true;
    }
}
