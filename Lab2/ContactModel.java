package invoices; 
import java.util.Objects; 
// контактная информация — 
// используется для представления информации о покупателе 
public class ContactModel extends AbstractBean { 
  public static final String PROP_NAME = "name"; 
  public static final String PROP_ADDRESS = "address"; 
  private String name = ""; 
  private String address = ""; 
  public String getName() { 
    return name; 
  } 
  public void setName(String newName) { 
    if (!Objects.equals(newName, name)) { 
      String oldName = name; 
      name = newName; 
      firePropertyChange(PROP_NAME, oldName, 
              newName); 
    } 
  } 
  public String getAddress() { 
    return address; 
  } 
  public void setAddress(String newAddress) { 
    if (!Objects.equals(newAddress, address)) { 
      String oldAddress = address; 
      address = newAddress; 
      firePropertyChange(PROP_ADDRESS, oldAddress, 
              newAddress);     } 
  } 
} 