package invoices; 
// действия, доступные контроллерам 
public interface ApplicationService { 
  boolean editInvoice(InvoiceModel model); 
  Period editPeriod(Period period); 
  void reportException(Exception exception); 
}