package dao.users;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<A> {

    void save(A a) throws SQLException;

    void save(UserProfile s) throws SQLException;

    Optional<A> find(int id) throws SQLException;

    List<A> findAll() throws SQLException;

    void delete(int id) throws SQLException;

    void update(A a) throws SQLException;

    void update(UserProfile s) throws SQLException;
}
