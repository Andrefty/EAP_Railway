package com.farcasanutudorandrei.collections;

import com.farcasanutudorandrei.domain.Parcel;

import java.util.ArrayList;

public class ParcelRepository implements GenericRepository<Parcel> {
    static private ArrayList<Parcel> storage = new ArrayList<Parcel>();
    @Override
    public void add(Parcel entity) {
        storage.add(entity);
    }

    @Override
    public Parcel get(int id) {
        return storage.get(id);

    }

    @Override
    public ArrayList<Parcel> getAll() {
        return storage;
    }

    @Override
    public void update(Parcel entityOld, Parcel entityNew) {
        storage.set(storage.indexOf(entityOld),entityNew);
    }

    @Override
    public void delete(Parcel entity) {
        storage.remove(entity);
    }

    @Override
    public int getSize() {
        return storage.size();
    }

    @Override
    public int getIndex(Parcel entity) {
        return storage.indexOf(entity);
    }
}
