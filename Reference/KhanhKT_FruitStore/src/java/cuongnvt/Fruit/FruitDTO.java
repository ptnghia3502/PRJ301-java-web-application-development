
package cuongnvt.Fruit;

import java.io.Serializable;

public class FruitDTO implements Serializable{
     private String ID;
     private String Name;
     private String Color;
     private String Description;
     private int Value;

    public FruitDTO() {
    }

    public FruitDTO(String ID, String Name, String Color, String Description, int Value) {
        this.ID = ID;
        this.Name = Name;
        this.Color = Color;
        this.Description = Description;
        this.Value = Value;
    }
 
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int Value) {
        this.Value = Value;
    }
     
     
     
     
}
