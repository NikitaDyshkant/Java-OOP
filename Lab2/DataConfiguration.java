import java.io.*; 
import java.sql.*; 
import java.util.Properties; 
// инкапсулирует установку соединения с БД на основе 
// параметров, загружаемых из файла, находящегося 
// в домашнем каталоге пользователя 
public class DataConfiguration { 
  private static DataConfiguration user; 
  private final String url; 
  private final Properties info; 
  private DataConfiguration(String url, Properties info) { 
    this.url = url; 
    this.info = info; 
  } 
  public Connection getConnection() throws SQLException { 
    return DriverManager.getConnection(url, info); 
  } 
  public String getUrl() { 
    return url; 
  } 
  public Properties getInfo() { 
    return info; 
  } 
  public static synchronized DataConfiguration user() 
          throws DataException { 
    if (null == user) { 
      user = createUser(); 
    } 
    return user; 
  } 
  private static DataConfiguration createUser() 
          throws DataException { 
    Properties p = new Properties(); 
    try { 
      System.out.println(System.getProperty("user.home")); 
      File file = new File(System.getProperty("user.home"), 
              ".invoices.properties"); 
      try (Reader reader = new FileReader(file)) { 
        p.load(reader); 
      } 
    } catch (FileNotFoundException ex) { 
      throw new DataException("Ask your administrator to" 
              + " configure this application.", ex); 
    } catch (IOException ex) { 
      throw new DataException(ex); 
    } 
    Properties info = new Properties();     info.setProperty("user", p.getProperty("user")); 
    info.setProperty("password",p.getProperty("password")); 
    info.setProperty("characterEncoding", "utf-8"); 
    return new DataConfiguration( 
            String.format("jdbc:mysql://%s:%s/%s", 
            p.getProperty("host"), 
            p.getProperty("port"), 
            p.getProperty("database")), info); 
  } 
}