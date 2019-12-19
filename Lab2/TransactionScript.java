package invoices; 
import java.sql.*; 
import java.sql.Date; 
import java.util.*; 
// запросы на языке SQL 
public class TransactionScript { 
  private TransactionScript() { 
  } 32 
 
  // ищет счета-фактуры за заданный период 
  public static List<InvoiceProxy> findInvoices(Period p, 
          Connection c) throws SQLException { 
    List<InvoiceProxy> proxies = new ArrayList<>(); 
    String sql = "SELECT * FROM invoice" 
            + " WHERE idate BETWEEN ? AND ?" 
            + " ORDER BY idate"; 
    try (PreparedStatement ps = c.prepareStatement(sql)) { 
      ps.setDate(1, new Date(p.getStartDate().getTime())); 
      ps.setDate(2, new Date(p.getEndDate().getTime())); 
      ResultSet rs = ps.executeQuery(); 
      while (rs.next()) { 
        proxies.add(invoiceProxyFromRow(rs, "")); 
      } 
    } 
    return proxies; 
  } 
  // ищет счет-фактуру по идентификатору 
  public static InvoiceModel loadInvoice(InvoiceProxy p, 
          Connection c) throws SQLException, 
          DataException { 
    String sql = "SELECT * FROM invoice WHERE id = ?"; 
    try (PreparedStatement ps = c.prepareStatement(sql)) { 
      ps.setInt(1, p.getId()); 
      ResultSet rs = ps.executeQuery(); 
      if (!rs.next()) { 
        throw new DataException("Not found."); 
      } 
      InvoiceModel m = fromRow(rs, "", new InvoiceModel()); 
      m.getLines().addAll(loadLineModels(p.getId(), c)); 
      return m; 
    } 
  } 
  // сохраняет новый счет-фактуру 
  public static void save(InvoiceModel m, Connection c) 
          throws SQLException, DataException { 
    assert m.getId() <= 0; 
    String sql = "INSERT INTO" 
            + " invoice(num, idate, sname, saddr)" 
            + " VALUES(?, ?, ?, ?)"; 
    try (PreparedStatement ps = c.prepareStatement(sql, 
            PreparedStatement.RETURN_GENERATED_KEYS)) { 
      setParameters(ps, 1, m); 
      if (1 != ps.executeUpdate()) { 
        throw new DataException("Insertion failed."); 
      } 
      ResultSet rs = ps.getGeneratedKeys(); 33 
 
      if (!rs.next()) { 
        throw new DataException("Insertion failed."); 
      } 
      int id = rs.getInt(1); 
      save(m.getLines(), id, c); 
      m.setId(id); 
    } 
  } 
  // обновляет ранее созданный счет-фактуру 
  public static void update(InvoiceModel m, Connection c) 
          throws SQLException, DataException { 
    assert m.getId() > 0; 
    String sql = "UPDATE invoice SET num = ?, idate = ?" 
            + ", sname = ?, saddr = ?, ver = ver + 1" 
            + " WHERE id = ? AND ver = ?"; 
    try (PreparedStatement ps = c.prepareStatement(sql)) { 
      int i = setParameters(ps, 1, m); 
      ps.setInt(i++, m.getId()); 
      ps.setInt(i++, m.getVersion()); 
      if (1 != ps.executeUpdate()) { 
        throw new DataException("Update failed."); 
      } 
      deleteInvoiceLines(m.getId(), c); 
      save(m.getLines(), m.getId(), c); 
      m.setVersion(m.getVersion() + 1); 
    } 
  } 
  // удаляет счет-фактуру 
  public static void delete(InvoiceProxy p, Connection c) 
          throws SQLException, DataException { 
    String sql = "DELETE FROM invoice" 
            + " WHERE id = ? AND ver = ?"; 
    try (PreparedStatement ps = c.prepareStatement(sql)) { 
      ps.setInt(1, p.getId()); 
      ps.setInt(2, p.getVersion()); 
      if (1 != ps.executeUpdate()) { 
        throw new DataException("Deletion failed."); 
      } 
    } 
  } 
  private static List<InvoiceLineModel> loadLineModels( 
          int id, Connection c) 
          throws SQLException, DataException { 
    String sql = "SELECT * FROM invoice_line" 
            + " WHERE id = ? ORDER BY ord"; 
    try (PreparedStatement ps = c.prepareStatement(sql)) { 
      ps.setInt(1, id); 34 
 
      ResultSet rs = ps.executeQuery(); 
      List<InvoiceLineModel> m = new ArrayList<>(); 
      while (rs.next()) { 
        m.add(fromRow(rs, "", new InvoiceLineModel())); 
      } 
      return m; 
    } 
  } 
  private static void save(List<InvoiceLineModel> m, 
          int id, Connection c) 
          throws SQLException, DataException { 
    String sql = "INSERT INTO" 
            + " invoice_line(id, ord, prod, qty, price)" 
            + " VALUES(?, ?, ?, ?, ?)"; 
    c.setAutoCommit(false); 
    try (PreparedStatement ps = c.prepareStatement(sql)) { 
      for (int i = 0; i < m.size(); ++i) { 
        ps.setInt(1, id); 
        ps.setInt(2, i); 
        setParameters(ps, 3, m.get(i)); 
        ps.addBatch(); 
      } 
      int[] counts = ps.executeBatch(); 
      for (int i = 0; i < counts.length; ++i) { 
        if (1 != counts[i]) { 
          throw new DataException("Insertion failed."); 
        } 
      } 
    } 
  } 
  private static void deleteInvoiceLines(int id, 
          Connection c) throws SQLException { 
    String sql = "DELETE FROM invoice_line WHERE id = ?"; 
    try (PreparedStatement ps = c.prepareStatement(sql)) { 
      ps.setInt(1, id); 
      ps.executeUpdate(); 
    } 
  } 
  private static InvoiceProxy invoiceProxyFromRow( 
          ResultSet rs, String p) throws SQLException { 
    return InvoiceProxy.createBuilder() 
            .id(rs.getInt(p + "id")) 
            .version(rs.getInt(p + "ver")) 
            .number(rs.getString(p + "num")) 
            .date(rs.getDate(p + "idate")) 
            .seller(rs.getString(p + "sname")) 
            .total(rs.getBigDecimal(p + "total"))             .build(); 
  } 
  private static InvoiceModel fromRow(ResultSet rs, 
          String p, InvoiceModel model) 
          throws SQLException { 
    model.setId(rs.getInt(p + "id")); 
    model.setVersion(rs.getInt(p + "ver")); 
    model.setNumber(rs.getString(p + "num")); 
    model.setDate(rs.getDate(p + "idate")); 
    fromRow(rs, "s", model.getSeller()); 
    return model; 
  } 
  private static InvoiceLineModel fromRow(ResultSet rs, 
          String p, InvoiceLineModel m) 
          throws SQLException { 
    m.setProduct(rs.getString(p + "prod")); 
    m.setQuantity(rs.getBigDecimal(p + "qty")); 
    m.setPrice(rs.getBigDecimal(p + "price")); 
    return m; 
  } 
  private static void fromRow(ResultSet rs, String p, 
          ContactModel m) throws SQLException { 
    m.setName(rs.getString(p + "name")); 
    m.setAddress(rs.getString(p + "addr")); 
  } 
  private static int setParameters(PreparedStatement ps, 
          int index, InvoiceModel m) throws SQLException { 
    ps.setString(index++, m.getNumber()); 
    ps.setDate(index++, new Date(m.getDate().getTime())); 
    index = setParameters(ps, index, m.getSeller()); 
    return index; 
  } 
  private static int setParameters(PreparedStatement ps, 
          int i, InvoiceLineModel m) throws SQLException { 
    ps.setString(i++, m.getProduct()); 
    ps.setBigDecimal(i++, m.getQuantity()); 
    ps.setBigDecimal(i++, m.getPrice()); 
    return i; 
  } 
  private static int setParameters(PreparedStatement ps, 
          int i, ContactModel m) throws SQLException { 
    ps.setString(i++, m.getName()); 
    ps.setString(i++, m.getAddress()); 
    return i; 
  } 
}