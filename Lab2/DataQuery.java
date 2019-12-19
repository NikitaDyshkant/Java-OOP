package invoices; 
import java.sql.*;
// используется для реализации «инверсии управления»  
// при обращении к БД  
public interface DataQuery<T> { 
  T perform(Connection connection) throws DataException, 
          SQLException; 
}