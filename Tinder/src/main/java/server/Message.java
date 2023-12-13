package server;

import java.sql.SQLException;

public interface Message {
    void put(Item x) throws SQLException;

    Iterable<Item> get() throws SQLException;

    default Iterable<Item> get(String userId) throws SQLException {
        return get();
    }
}
