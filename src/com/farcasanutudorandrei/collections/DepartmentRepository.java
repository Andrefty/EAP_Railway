package com.farcasanutudorandrei.collections;

import com.farcasanutudorandrei.domain.Department;

import java.util.ArrayList;

public class DepartmentRepository implements GenericRepository<Department> {
    private ArrayList<Department> storage = new ArrayList<Department>();

    @Override
    public void add(Department entity) {
        storage.add(entity);
    }

    @Override
    public Department get(int id) {
        return storage.get(id);
    }

    @Override
    public void update(Department entityOld, Department entityNew) {
        storage.set(storage.indexOf(entityOld),entityNew);
    }

    @Override
    public void delete(Department entity) {
        storage.remove(entity);

    }

    @Override
    public int getSize() {
        return storage.size();
    }
}
