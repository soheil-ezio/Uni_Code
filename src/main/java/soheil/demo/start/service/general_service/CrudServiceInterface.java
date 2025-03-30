package soheil.demo.start.service.general_service;

import java.util.List;
import java.util.Optional;

/*
/   A Generic Interface for setting a ground "RULE"!!
/   The Rule :
/       Every class that wants to have ABSTRACT crud functionality,
/       must obey these methods
*/

public interface CrudServiceInterface<T, ID> {

    //Methods.
    //-------------------------------------------------------------------------------

    public T add(T entity);
    public T update(T entity, ID id);
    public void delete(T entity);
    Optional<T> findById(ID id);
    public List<T> findAll();

    //-------------------------------------------------------------------------------
}
