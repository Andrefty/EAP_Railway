package com.farcasanutudorandrei.collections;

import com.farcasanutudorandrei.domain.Passenger;

import java.util.ArrayList;

public class PassengerRepository implements GenericRepository<Passenger> {
    private ArrayList<Passenger> storage=new ArrayList<Passenger>();
    @Override
    public void add(Passenger entity) {
        storage.add(entity);
    }

    @Override
    public Passenger get(int id) {
        return storage.get(id);
    }

    @Override
    public ArrayList<Passenger> getAll() {
        return storage;
    }

    @Override
    public void update(Passenger entityOld, Passenger entityNew) {
storage.set(storage.indexOf(entityOld),entityNew);
    }

    @Override
    public void delete(Passenger entity) {
storage.remove(entity);
    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    public int getIndex(Passenger entity) {
        return storage.indexOf(entity);
    }
}
