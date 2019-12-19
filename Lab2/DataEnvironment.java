package invoices; 
import java.awt.event.*; 
import java.sql.*; 
import javax.swing.Timer;  
// инкапсулирует управление соединение с БД: для снижения 
// нагрузки на сервер БД в случае неактивности пользователя 
// в течении 5 минут соединение автоматически закрывается  

public class DataEnvironment { 
  public static final int DELAY = 5 * 60 * 1000; 
  private Connection connection; 
  private final Timer timer; 
  private static ThreadLocal<DataEnvironment> current; 
  private DataEnvironment() { 
    timer = new Timer(DELAY, new ActionListener() { 
      @Override 
      @SuppressWarnings("CallToThreadDumpStack") 
      public void actionPerformed(ActionEvent e) { 
        try { 
          connection.close(); 
          connection = null; 
        } catch (SQLException ex) { 
          ex.printStackTrace(); 
        } 
      } 
    }); 
    timer.setRepeats(false); 
  } 
  public static DataEnvironment current() { 
    return current.get(); 
  } 
  public static void setCurrent( 
          DataEnvironment newCurrent) { 
    current.set(newCurrent); 
  }   public <T> T withinTransaction(DataQuery<T> query) 
          throws DataException, SQLException { 
    Connection c = getConnection(); 
    c.setAutoCommit(false); 
    boolean succeed = false; 
    try { 
      T result = query.perform(c); 
      succeed = true; 
      return result; 
    } finally { 
      if (succeed) { 
        c.commit(); 
      } else { 
        c.rollback(); 
      } 
    } 
  } 
  private Connection getConnection() throws SQLException, 
          DataException { 
    if (null == connection) { 
      connection = 
          DataConfiguration.user().getConnection(); 
      timer.restart(); 
    } 
    return connection; 
  } 
  static { 
    current = new ThreadLocal<DataEnvironment>() { 
      @Override 
      protected DataEnvironment initialValue() { 
        return new DataEnvironment(); 
      } 
    }; 
  } 
}