package cuongnvt.Cart;


import cuongnvt.Fruit.FruitDTO;
import java.io.Serializable;


public class CartDTO implements Serializable{
    private FruitDTO fruit;
    private Integer quantity;

    public CartDTO() {
    }

    public CartDTO(FruitDTO fruit, Integer quantity) {
        this.fruit = fruit;
        this.quantity = quantity;
    }

    public FruitDTO getFruit() {
        return fruit;
    }

    public void setFruit(FruitDTO fruit) {
        this.fruit = fruit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    
}
