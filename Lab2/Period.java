package invoices; 
// контроллер диалогового окна редактирования счета-фактуры 
public class InvoiceController extends AbstractBean { 
  public static final String PROP_MODEL = "model"; 
  public static final String PROP_SELECTED_LINE_MODEL = 
          "selectedLineModel"; 
  private final InvoiceModel model; 
  private InvoiceLineModel selectedLineModel; 
  public InvoiceController() { 
    this(new InvoiceModel()); 
  } 
  public InvoiceController(InvoiceModel model) { 
    this.model = model; 
  } 
  public InvoiceModel getModel() { 
    return model; 
  } 
  public InvoiceLineModel getSelectedLineModel() { 
    return selectedLineModel; 
  } 
  public void setSelectedLineModel( 
          InvoiceLineModel newSelectedLineModel) { 
    selectedLineModel = newSelectedLineModel; 
  } 
  // вызывается при нажатии на кнопку «Добавить» 
  public void addLine() { 
    getModel().getLines().add(new InvoiceLineModel()); 
  } 
  // вызывается при нажатии на кнопку «Удалить» 
  public void deleteLine() { 
    if (null != selectedLineModel) { 
      getModel().getLines().remove(selectedLineModel); 
    } 
  } 
} 
12. Файл Period.java 
pimport java.util.*; 
// период во времени 
public class Period { 
  private final Date start; 
  private final Date end; 
  public Period(Date start, Date end) { 
    assert !start.after(end); 
    this.start = (Date) start.clone(); 
    this.end = (Date) end.clone(); 
  } 
  public Date getStartDate() { 
    return start; 
  } 
  public Date getEndDate() { 
    return end; 
  } 
  @Override 
  public int hashCode() { 
    int hash = 5; 
    hash = 71 * hash + Objects.hashCode(start); 
    hash = 71 * hash + Objects.hashCode(end); 
    return hash; 
  } 
  @Override 
  public boolean equals(Object other) { 
    return this == other || null != other 
            && other instanceof Period 
            && contentEquals((Period) other); 
  } 
  private boolean contentEquals(Period other) { 
    return Objects.equals(start, other.start) 
            && Objects.equals(end, other.end); 
  } 
}