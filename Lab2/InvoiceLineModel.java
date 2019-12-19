package invoices; 
import java.math.BigDecimal; 
import java.util.Objects; 
// полная информации о счете-фактуре,  
// используемая в диалоговом окне редактирования 
public class InvoiceLineModel extends AbstractBean { 
  public static final String PROP_PRODUCT = "product"; 
  public static final String PROP_QUANTITY = "quantity"; 
  public static final String PROP_PRICE = "price"; 
  public static final String PROP_AMOUNT = "amount"; 
  private InvoiceModel parent; 
  private String product = ""; 
  private BigDecimal quantity = BigDecimal.ZERO; 
  private BigDecimal price = BigDecimal.ZERO; 28 
 
  private BigDecimal amount; 
  public InvoiceLineModel() { 
    amount = computeAmount(); 
  } 
  public InvoiceModel getParent() { 
    return parent; 
  } 
  public String getProduct() { 
    return product; 
  } 
  public void setProduct(String newProduct) { 
    if (!Objects.equals(newProduct, product)) { 
      String oldFreight = product; 
      product = newProduct; 
      firePropertyChange(PROP_PRODUCT, oldFreight, 
              newProduct); 
    } 
  } 
  public BigDecimal getQuantity() { 
    return quantity; 
  } 
  public void setQuantity(BigDecimal newQuantity) { 
    if (!Objects.equals(newQuantity, quantity)) { 
      BigDecimal oldQuantity = quantity; 
      quantity = newQuantity; 
      firePropertyChange(PROP_QUANTITY, oldQuantity, 
              newQuantity); 
      setAmount(computeAmount()); 
    } 
  } 
  public BigDecimal getPrice() { 
    return price; 
  } 
  public void setPrice(BigDecimal newPrice) { 
    if (!Objects.equals(newPrice, price)) { 
      BigDecimal oldPrice = price; 
      price = newPrice; 
      firePropertyChange(PROP_PRICE, oldPrice, newPrice); 
      setAmount(computeAmount()); 
    } 
  } 
  public BigDecimal getAmount() { 
    return amount; 
  } 
  void setParent(InvoiceModel newParent) { 
    parent = newParent; 
  }   private void setAmount(BigDecimal newAmount) { 
    if (!Objects.equals(newAmount, amount)) { 
      BigDecimal oldAmount = amount; 
      amount = newAmount; 
      firePropertyChange(PROP_AMOUNT, oldAmount, 
              newAmount); 
      if (null != parent) { 
        parent.lineAmountChanged(this, oldAmount); 
      } 
    } 
  } 
  private BigDecimal computeAmount() { 
    return quantity.multiply(price); 
  } 
}