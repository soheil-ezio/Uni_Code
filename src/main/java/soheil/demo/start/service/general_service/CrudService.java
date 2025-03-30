package soheil.demo.start.service.general_service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/*
/   A Generic Abstract class for CRUD functionality
*/

public abstract class CrudService<T, ID> implements CrudServiceInterface<T, ID>{

    protected final JpaRepository<T, ID> repository;

    public CrudService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T add(T entity) {
        return repository.save(entity);
    }

    @Override
    public T update(T entity, ID username) {
        return repository.save(entity);
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public Optional<T> findById(ID username) {
        return repository.findById(username);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }
}
