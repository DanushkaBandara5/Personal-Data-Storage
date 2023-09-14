package lk.ijse.app.personal.dao;

import lk.ijse.app.personal.entity.SuperEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

// This interface is used to define common functionalities of the DAO classes
public interface SuperDAO<T extends SuperEntity, Id extends Serializable> {
    T save(T entity);

    T update(T entity);

    void deleteById(Id id);

    Optional<T> findById(Id id);

    List<T> filterEntity(String query);

    long count();

    List<T> findAll();

    boolean existById(Id id);

}
