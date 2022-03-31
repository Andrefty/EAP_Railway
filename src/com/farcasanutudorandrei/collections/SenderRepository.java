package com.farcasanutudorandrei.collections;

import com.farcasanutudorandrei.domain.Sender;

import java.util.ArrayList;

public class SenderRepository implements GenericRepository<Sender> {
    private ArrayList<Sender> storage = new ArrayList<Sender>();


    @Override
    public void add(Sender entity) {
        storage.add(entity);
    }

    @Override
    public Sender get(int id) {
        return storage.get(id);
    }

    @Override
    public void update(Sender entityOld, Sender entityNew) {
        storage.set(storage.indexOf(entityOld),entityNew);
    }

    @Override
    public void delete(Sender entity) {
        storage.remove(entity);
    }

    @Override
    public int getSize() {
        return storage.size();
    }
}
