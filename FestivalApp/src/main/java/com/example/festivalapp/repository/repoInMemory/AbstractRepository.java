package com.example.festivalapp.repository.repoInMemory;

import com.example.festivalapp.domain.Entity;
import com.example.festivalapp.repository.Repository;
import com.example.festivalapp.repository.RepositoryException;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AbstractRepository<ID,T extends Entity<ID>> implements Repository<ID , T> {
    protected Map<ID,T> elem;

    public AbstractRepository(){
        elem= new HashMap<>();

    }
    @Override
    public T findById(ID id) throws SQLException {
        if(elem.containsKey(id))
            return elem.get(id);
        else throw new RepositoryException("Element doesn't exist " + id);
    }

    @Override
    public Iterable<T> findAll() throws SQLException {
        return elem.values();
    }

    @Override
    public void save(T entity) throws SQLException {
        if(elem.containsKey(entity.getId())){
            throw new RepositoryException("Element already exist " + entity);
        }else elem.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        if(elem.containsKey(entity.getId()))
            elem.remove(entity.getId());
    }

    @Override
    public void update(ID id, T entity) throws SQLException {
        if(elem.containsKey(id))
            elem.put(entity.getId(), entity);
        else throw new RepositoryException("Element doesn't exit " + id);
    }

    @Override
    public Collection<T> getAll() {
        return elem.values();
    }
}
