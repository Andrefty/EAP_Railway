package com.farcasanutudorandrei.collections;

import com.farcasanutudorandrei.domain.Sender;

import java.util.ArrayList;

public class SenderRepository implements GenericRepository<Sender> {
    static private ArrayList<Sender> storage = new ArrayList<Sender>();


    @Override
    public void add(Sender entity) {
        storage.add(entity);
    }

    @Override
    public Sender get(int id) {
        return storage.get(id);
    }
//TODO: replace this
//    public Sender getbyDBid(int id) {
//        for (Sender sender : storage) {
//            if (sender.getId_expeditor() == id) {
//                return sender;
//            }
//        }
//        return null;
//    }

    @Override
    public ArrayList<Sender> getAll() {
        return storage;
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

    @Override
    public int getIndex(Sender entity) {
        return storage.indexOf(entity);
    }
}
