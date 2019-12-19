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