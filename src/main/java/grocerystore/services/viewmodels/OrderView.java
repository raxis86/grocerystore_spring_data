package grocerystore.services.viewmodels;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by raxis on 26.12.2016.
 * Модель для представления заказа со списком продуктов
 * и возможными статусами
 */
public class OrderView {
    private static final Logger logger = LoggerFactory.getLogger(OrderView.class);

    private String id;
    private String fullName;
    private String address;
    private String date;
    private String status;
    private String price;
    private Map<String,Integer> groceries;
    private Map<String,String> statuses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Map<String, Integer> getGroceries() {
        return groceries;
    }

    public void setGroceries(Map<String, Integer> groceries) {
        this.groceries = groceries;
    }

    public Map<String, String> getStatuses() {
        return statuses;
    }

    public void setStatuses(Map<String, String> statuses) {
        this.statuses = statuses;
    }
}
