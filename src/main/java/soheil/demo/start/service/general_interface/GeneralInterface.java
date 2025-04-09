package soheil.demo.start.service.general_interface;

import jakarta.persistence.Entity;
import soheil.demo.start.model.University;

import java.util.List;
import java.util.Optional;

public interface GeneralInterface<T> {
    public T get(String name);
    public String getAll();
    public String add(String name, String name1);
    public String addMultiple(List<String> names, String name);
    public void remove(String name);
    public boolean isPresent(String name);
}
