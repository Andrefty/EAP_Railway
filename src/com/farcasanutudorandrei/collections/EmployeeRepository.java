package com.farcasanutudorandrei.collections;

import com.farcasanutudorandrei.domain.Employee;

import java.util.ArrayList;

public class EmployeeRepository implements GenericRepository<Employee> {

    private ArrayList<Employee> storage = new ArrayList<Employee>();

    @Override
    public void add(Employee entity) {
        storage.add(entity);
    }

    @Override
    public Employee get(int id) {
        return storage.get(id);
    }

    @Override
    public void update(Employee entityOld, Employee entityNew) {
storage.set(storage.indexOf(entityOld),entityNew);
    }

    @Override
    public void delete(Employee entity) {
storage.remove(entity);
    }

    @Override
    public int getSize() {
        return storage.size();
    }
}
