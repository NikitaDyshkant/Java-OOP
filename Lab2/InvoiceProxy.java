package invoices; 
import java.math.BigDecimal; 
import java.util.Date; 
// сокращенная информации о счете-фактуре,  
// используемая в главном окне 
public class InvoiceProxy { 
  private final int id; 
  private final int version; 
  private final String number; 
  private final Date date; 
  private final String seller; 
  private final BigDecimal total; 
  private InvoiceProxy(Builder builder) { 
    id = builder.id; 
    version = builder.version; 
    number = builder.number; 
    date = builder.date; 
    seller = builder.seller; 
    total = builder.total; 
  } 
  public int getId() { 
    return id; 
  } 
  public int getVersion() { 
    return version; 
  } 
  public String getNumber() { 
    return number; 
  } 
  public Date getDate() { 
    return date; 
  } 
  public String getSeller() { 
    return seller; 
  } 
  public BigDecimal getTotal() { 
    return total; 
  } 
  public static Builder createBuilder() { 
    return new Builder(); 
  }   public static class Builder { 
    private int id; 
    private int version; 
    private String number; 
    private Date date; 
    private String seller; 
    private BigDecimal total; 
    public Builder id(int id) { 
      this.id = id; 
      return this; 
    } 
    public Builder version(int version) { 
      this.version = version; 
      return this; 
    } 
    public Builder number(String number) { 
      this.number = number; 
      return this; 
    } 
    public Builder date(Date date) { 
      this.date = (Date) date.clone(); 
      return this; 
    } 
    public Builder seller(String seller) { 
      this.seller = seller; 
      return this; 
    } 
    public Builder total(BigDecimal total) { 
      this.total = total; 
      return this; 
    } 
    public InvoiceProxy build() { 
      return new InvoiceProxy(this); 
    } 
  } 
}