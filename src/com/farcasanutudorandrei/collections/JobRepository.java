package com.farcasanutudorandrei.collections;

import com.farcasanutudorandrei.domain.Job;

import java.util.ArrayList;

public class JobRepository implements GenericRepository<Job> {
    private ArrayList<Job> storage= new ArrayList<Job>();
    @Override
    public void add(Job entity) {
        storage.add(entity);
    }

    @Override
    public Job get(int id) {
        return storage.get(id);
    }

    @Override
    public void update(Job entityOld, Job entityNew) {
        storage.set(storage.indexOf(entityOld),entityNew);
    }

    @Override
    public void delete(Job entity) {
        storage.remove(entity);
    }

    @Override
    public int getSize() {
        return storage.size();
    }
}
