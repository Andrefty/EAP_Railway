package com.farcasanutudorandrei.collections;

import com.farcasanutudorandrei.domain.Job;
import com.farcasanutudorandrei.domain.Station;

import java.util.ArrayList;

public class JobRepository implements GenericRepository<Job> {
    static private ArrayList<Job> storage= new ArrayList<Job>();
    @Override
    public void add(Job entity) {
        storage.add(entity);
    }

    @Override
    public Job get(int id) {
        return storage.get(id);
    }

    @Override
    public ArrayList<Job> getAll() {
        return storage;
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

    @Override
    public int getIndex(Job entity) {
        return storage.indexOf(entity);
    }

    public Job findById(int id) {
        return storage.stream().filter(entity -> entity.getId_job() == id).findFirst().get();
    }
}
