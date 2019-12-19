package invoices; 
import java.util.*; 
// контроллер диалогового окна редактирования периода 
public class PeriodController extends AbstractBean { 
  public static final String PROP_START_DATE = "startDate"; 
  public static final String PROP_END_DATE = "endDate"; 
  private Date startDate; 
  private Date endDate; 
  public PeriodController() { 
    startDate = new Date();     endDate = new Date(); 
  } 
  public Period toPeriod() { 
    return new Period(startDate, endDate); 
  } 
  public void applyPeriod(Period period) { 
    setStartDate(period.getStartDate()); 
    setEndDate(period.getEndDate()); 
  } 
  public Date getStartDate() { 
    return (Date) startDate.clone(); 
  } 
  public void setStartDate(Date newStartDate) { 
    if (!Objects.equals(newStartDate, startDate)) { 
      Date oldStartDate = startDate; 
      startDate = newStartDate; 
      firePropertyChange(PROP_START_DATE, oldStartDate, 
              newStartDate); 
    } 
  } 
  public Date getEndDate() { 
    return (Date) endDate.clone(); 
  } 
  public void setEndDate(Date newEndDate) { 
    if (!Objects.equals(newEndDate, endDate)) { 
      Date oldEndDate = endDate; 
      endDate = newEndDate; 
      firePropertyChange(PROP_END_DATE, oldEndDate, 
              newEndDate); 
    } 
  } 
  public void set(Period period) { 
    setStartDate(period.getStartDate()); 
    setEndDate(period.getEndDate()); 
  } 
}