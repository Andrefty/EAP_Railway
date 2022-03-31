package com.farcasanutudorandrei.collections;

import com.farcasanutudorandrei.domain.Station;

import java.util.ArrayList;

public class StationRepository implements GenericRepository<Station> {
    private ArrayList<Station> storage = new ArrayList<Station>();
    @Override
    public void add(Station entity) {
        storage.add(entity);
    }

    @Override
    public Station get(int id) {
        return storage.get(id);
    }

    @Override
    public void update(Station entityOld, Station entityNew) {
        storage.set(storage.indexOf(entityOld),entityNew);
    }

    @Override
    public void delete(Station entity) {
        storage.remove(entity);
    }

    @Override
    public int getSize() {
        return storage.size();
    }
}
