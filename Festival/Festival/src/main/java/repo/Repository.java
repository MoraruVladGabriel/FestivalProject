package repo;

import domain.Entity;

import java.sql.SQLException;
import java.util.Optional;

/**
 * CRUD operations repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <T> -  type of entities saved in repository
 */
public interface Repository<ID, T> {
    /**
     *
     * @param id -the id of the entity to be returned
     *           id must not be null
     * @return an {T} entity with the given id
     * @throws IllegalArgumentException
     *                  if id is null.
     */
    T findById(ID id) throws SQLException;

    /**
     *
     * @return all entities
     */
    Iterable<T> findAll() throws SQLException;

    /**
     *
     * @param entity
     *         entity must be not null
     * @throws IllegalArgumentException
     *             if the given entity is null.     *
     */
    void save(T entity) throws SQLException;


    /**
     *  removes the entity with the specified id
     * @param entity
     *      entity must be not null
     * @throws IllegalArgumentException
     *                   if the given id is null.
     */
    void delete(T entity);

    /**
     * @param id
     * @param entity entity must not be null
     * @throws IllegalArgumentException if the given entity is null.
     */
    void update(ID id, T entity) throws SQLException;
}
