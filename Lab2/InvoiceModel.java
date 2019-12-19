package invoices; 
import java.math.BigDecimal; 
import java.util.*; 
import org.jdesktop.observablecollections.*; 
// строка счета-фактуры
public class InvoiceModel extends AbstractBean { 
  public static final String PROP_NUMBER = "number"; 
  public static final String PROP_DATE = "date"; 
  public static final String PROP_TOTAL = "total"; 
  private int id; 
  private int version; 
  private String number = ""; 
  private Date date = new Date(System.currentTimeMillis()); 
  private final ContactModel seller; 
  private final ObservableList<InvoiceLineModel> lines; 
  private BigDecimal total = BigDecimal.ZERO; 
  public InvoiceModel() { 
    seller = new ContactModel(); 
    lines = ObservableCollections.observableList( 
            new ArrayList<InvoiceLineModel>()); 
    lines.addObservableListListener(new LinesListener()); 
  } 
  public int getId() { 
    return id; 
  } 
  public void setId(int newId) { 
    id = newId; 
  } 
  public int getVersion() { 
    return version; 
  } 
  public void setVersion(int newVersion) { 
    version = newVersion; 
  } 
  public String getNumber() { 
    return number; 
  } 
  public void setNumber(String newNumber) { 
    if (!Objects.equals(newNumber, number)) { 
      String oldNumber = number; 
      number = newNumber; 
      firePropertyChange(PROP_NUMBER, oldNumber, 
              newNumber); 
    } 26 
 
  } 
  public Date getDate() { 
    return (Date) date.clone(); 
  } 
  public void setDate(Date newDate) { 
    if (!Objects.equals(newDate, date)) { 
      Date oldDate = date; 
      date = (Date) newDate.clone(); 
      firePropertyChange(PROP_DATE, oldDate, 
              newDate); 
    } 
  } 
  public ContactModel getSeller() { 
    return seller; 
  } 
  public ObservableList<InvoiceLineModel> getLines() { 
    return lines; 
  } 
  public BigDecimal getTotal() { 
    return total; 
  } 
  public InvoiceProxy createProxy() { 
    return InvoiceProxy.createBuilder().id(id) 
            .version(version).number(number).date(date) 
            .seller(seller.getName()).total(total) 
            .build(); 
  } 
  void lineAmountChanged(InvoiceLineModel line, 
          BigDecimal oldAmount) { 
    setTotal(total.add(line.getAmount() 
            .subtract(oldAmount))); 
  } 
  private void setTotal(BigDecimal newTotal) { 
    if (!Objects.equals(newTotal, total)) { 
      BigDecimal oldTotal = total; 
      total = newTotal; 
      firePropertyChange(PROP_TOTAL, oldTotal, newTotal); 
    } 
  } 
  private void lineAdded(int lineIndex) { 
    InvoiceLineModel line = lines.get(lineIndex); 
    line.setParent(this); 
    setTotal(total.add(line.getAmount())); 
  } 
  private void lineRemoved(Object lineElement) { 
    InvoiceLineModel line = (InvoiceLineModel) lineElement; 
    setTotal(total.subtract(line.getAmount()));     line.setParent(null); 
  } 
  private class LinesListener 
          implements ObservableListListener { 
    @Override 
    public void listElementsAdded(ObservableList list, 
            int index, int length) { 
      for (int i = 0; i < length; ++i) { 
        lineAdded(index + i); 
      } 
    } 
    @Override 
    public void listElementsRemoved(ObservableList list, 
            int index, List oldElements) { 
      for (int i = 0; i < oldElements.size(); ++i) { 
        lineRemoved(oldElements.get(i)); 
      } 
    } 
    @Override 
    public void listElementReplaced(ObservableList list, 
            int index, Object oldElement) { 
      lineRemoved(oldElement); 
      lineAdded(index); 
    } 
    @Override 
    public void listElementPropertyChanged( 
            ObservableList list, int index) { 
      throw new RuntimeException("Should never happen."); 
    } 
  } 
}