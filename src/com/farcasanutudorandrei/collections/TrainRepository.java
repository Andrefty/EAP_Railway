package com.farcasanutudorandrei.collections;

import com.farcasanutudorandrei.domain.Train;

import java.util.ArrayList;

public class TrainRepository implements GenericRepository<Train> {
    private ArrayList<Train> storage = new ArrayList<Train>();
    @Override
    public void add(Train entity) {
        storage.add(entity);
    }

    @Override
    public Train get(int id) {
        return storage.get(id);
    }

    @Override
    public void update(Train entityOld, Train entityNew) {
        storage.set(storage.indexOf(entityOld),entityNew);
    }

    @Override
    public void delete(Train entity) {
        storage.remove(entity);
    }

    @Override
    public int getSize() {
        return storage.size();
    }
}
