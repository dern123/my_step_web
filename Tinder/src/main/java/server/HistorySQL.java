package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class HistorySQL implements History {

  private final Connection conn;

  public HistorySQL(Connection conn) {
    this.conn = conn;
  }

  @Override
  public void put(Item a) throws SQLException {
    PreparedStatement st = conn.prepareStatement("INSERT INTO log (x, op, y, z, user_id) values (?,?,?,?,?)");
    st.setInt(1, a.x());
    st.setString(2, a.op());
    st.setInt(3, a.y());
    st.setInt(4, a.result());
    st.setString(5, a.user_id());
    st.executeUpdate();
  }

  private Iterable<Item> resultSetToIterable(ResultSet rs) {
    return () -> new Iterator<>() {
      @Override
      public boolean hasNext() {
        try {
          return rs.next();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public Item next() {
        try {
          return new Item(
            rs.getInt("x"),
            rs.getInt("y"),
            rs.getString("op"),
            rs.getInt("z"),
            rs.getString("user_id")
          );
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
      }
    };
  }


  @Override
  public Iterable<Item> get() throws SQLException {
    PreparedStatement st = conn.prepareStatement("SELECT x, op, y, z, user_id from log");
    ResultSet rs = st.executeQuery();
    return resultSetToIterable(rs);
  }

  @Override
  public Iterable<Item> get(String userId) throws SQLException {
    PreparedStatement st = conn.prepareStatement("SELECT x, op, y, z, user_id from log where user_id = ?");
    st.setString(1, userId);
    ResultSet rs = st.executeQuery();
    return resultSetToIterable(rs);
  }
}
