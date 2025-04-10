package soheil.demo.start.service.general_interface;

import java.util.List;

public interface GeneralInterface<T> {
    public T get(String name);
    public String getAll();
    public String add(String name, String name1);
    public String addMultiple(List<String> names, String name);
    public void remove(String name);
    public boolean isPresent(String name);
}
