package grocerystore.services.models;

import grocerystore.domain.models.Grocery_model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by raxis on 25.12.2016.
 * Корзина покупок
 */
public class Cart {
    private static final Logger logger = LoggerFactory.getLogger(Cart.class);

    /**
     * Хэш-мап для огранизации хранения товаров
     */
    private Map<Grocery_model,Integer> map = new HashMap<>();

    public Map<Grocery_model, Integer> getMap() {
        return map;
    }

    /**
     * Добавление товара
     * @param groceryModel - продукт
     * @param quantity - количество
     */
    public void addItem(Grocery_model groceryModel, Integer quantity){
        Integer sum=map.put(groceryModel,quantity);
        if(sum!=null){
            map.put(groceryModel,quantity+sum);
        }
    }

    public void removeItem(Grocery_model groceryModel){
        map.remove(groceryModel);
    }

    /**
     * Подсчет общей суммы корзины
     * @return
     */
    public BigDecimal computeTotalPrice(){
        BigDecimal priceSum = BigDecimal.valueOf(0);
        BigDecimal price=BigDecimal.valueOf(0);
        BigDecimal quan=BigDecimal.valueOf(0);

        for(HashMap.Entry entry: map.entrySet()){
            price=((Grocery_model)entry.getKey()).getPrice();
            quan=BigDecimal.valueOf((Integer)entry.getValue());
            priceSum=priceSum.add(price.multiply(quan));
        }
        return priceSum;
    }

    /**
     * Подсчет общего количества продуктов в корзине
     * @return
     */
    public int totalQuantity(){
        int totalQuantity=0;
        for(HashMap.Entry entry: map.entrySet()){
            totalQuantity+=(int)entry.getValue();
        }
        return totalQuantity;
    }

    public void clear(){
        map.clear();
    }

}
