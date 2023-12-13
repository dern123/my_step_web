package server;

import java.sql.SQLException;

public interface History {

  void put(Item x) throws SQLException;

  Iterable<Item> get() throws SQLException;

  default Iterable<Item> get(String userId) throws SQLException {
    return get();
  }

}
