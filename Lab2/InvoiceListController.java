package invoices; 
import java.sql.Connection; 
import java.util.*; 
import java.sql.SQLException; 
import org.jdesktop.observablecollections.*; 
// контроллер главного окна 
public class InvoiceListController extends AbstractBean { 
  public static final String PROP_PERIOD = "period"; 
  public static final String PROP_COUNT = "count"; 
  private final DataEnvironment environment; 
  private Period period; 
  private final ObservableList<InvoiceProxy> proxies; 
  private int count; 
  private InvoiceProxy selectedProxy; 
  private ApplicationService service; 
  public InvoiceListController() { 
    this(DataEnvironment.current()); 
  } 
  public InvoiceListController( 
          DataEnvironment environment) { 
    this.environment = environment; 
    Date date = new Date(); 
    period = new Period(date, date); 
    this.proxies = ObservableCollections.observableList( 
            new ArrayList<InvoiceProxy>()); 
  } 
  public Period getPeriod() { 
    return period; 
  } 
  public void setPeriod(Period newPeriod) { 
    if (!Objects.equals(newPeriod, period)) { 
      Period oldPeriod = period; 
      period = newPeriod; 
      firePropertyChange(PROP_PERIOD, oldPeriod, 
              newPeriod); 
      refresh(); 37 
 
    } 
  } 
  public InvoiceProxy getSelectedProxy() { 
    return selectedProxy; 
  } 
  public void setSelectedProxy( 
          InvoiceProxy newSelectedProxy) { 
    selectedProxy = newSelectedProxy; 
  } 
  public ObservableList<InvoiceProxy> getProxies() { 
    return proxies; 
  } 
  public int getCount() { 
    return count; 
  } 
  public ApplicationService getService() { 
    return service; 
  } 
  public void setService(ApplicationService newService) { 
    service = newService; 
  } 
  // вызывается при нажатии на кнопку «Добавить» 
  public void add() { 
    try { 
      InvoiceModel model = new InvoiceModel(); 
      if (!service.editInvoice(model)) { 
        return; 
      } 
      doAdd(model); 
      proxies.add(model.createProxy()); 
    } catch (SQLException | DataException ex) { 
      service.reportException(ex); 
    } 
    setCount(proxies.size()); 
  } 
  // вызывается при нажатии на кнопку «Редактировать» 
  public void edit() { 
    if (null == selectedProxy) { 
      return; 
    } 
    try { 
      InvoiceModel model = doLoad(selectedProxy); 
      if (!service.editInvoice(model)) { 
        return; 
      } 
      doUpdate(model); 
      InvoiceProxy proxy = model.createProxy(); 38 
 
      proxies.set(proxies.indexOf(selectedProxy), proxy); 
      selectedProxy = proxy; 
    } catch (SQLException | DataException ex) { 
      service.reportException(ex); 
    } 
  } 
  // вызывается при нажатии на кнопку «Удалить» 
  public void delete() { 
    if (null == selectedProxy) { 
      return; 
    } 
    try { 
      doDelete(selectedProxy); 
    } catch (DataException | SQLException ex) { 
      service.reportException(ex); 
    } 
    proxies.remove(selectedProxy); 
    setCount(proxies.size()); 
  } 
  // вызывается при нажатии на кнопку «Обновить» 
  public void refresh() { 
    proxies.clear(); 
    try { 
      proxies.addAll(doFind()); 
    } catch (SQLException | DataException ex) { 
      service.reportException(ex); 
    } 
    setCount(proxies.size()); 
  } 
  // вызывается при нажатии на кнопку «Период...» 
  public void choosePeriod() { 
    Period newPeriod = service.editPeriod(period); 
    if (null == newPeriod) { 
      return; 
    } 
    setPeriod(newPeriod); 
  } 
  private void setCount(int newCount) { 
    if (newCount != count) { 
      int oldCount = count; 
      count = newCount; 
      firePropertyChange(PROP_COUNT, oldCount, newCount); 
    } 
  } 
  private List<InvoiceProxy> doFind() 
          throws SQLException, DataException { 
    return environment.withinTransaction( 39 
 
            new DataQuery<List<InvoiceProxy>>() { 
      @Override 
      public List<InvoiceProxy> perform(Connection c) 
              throws DataException, SQLException { 
        return TransactionScript.findInvoices(period, c); 
      } 
    }); 
  } 
  private void doAdd(final InvoiceModel model) 
          throws SQLException, DataException { 
    environment.withinTransaction(new DataQuery<Void>() { 
      @Override 
      public Void perform(Connection c) 
              throws DataException, SQLException { 
        TransactionScript.save(model, c); 
        return null; 
      } 
    }); 
  } 
  private InvoiceModel doLoad(final InvoiceProxy proxy) 
          throws DataException, SQLException { 
    return environment.withinTransaction( 
            new DataQuery<InvoiceModel>() { 
      @Override 
      public InvoiceModel perform(Connection c) 
              throws DataException, SQLException { 
        return TransactionScript.loadInvoice(proxy, c); 
      } 
    }); 
  } 
  private void doUpdate(final InvoiceModel model) 
          throws SQLException, DataException { 
    environment.withinTransaction(new DataQuery<Void>() { 
      @Override 
      public Void perform(Connection c) 
              throws DataException, SQLException { 
        TransactionScript.update(model, c); 
        return null; 
      } 
    }); 
  } 
  private void doDelete(final InvoiceProxy proxy) 
          throws DataException, SQLException { 
    environment.withinTransaction(new DataQuery<Void>() { 
      @Override 
      public Void perform(Connection c) 
              throws DataException, SQLException {         TransactionScript.delete(proxy, c); 
        return null; 
      } 
    }); 
  } 
}