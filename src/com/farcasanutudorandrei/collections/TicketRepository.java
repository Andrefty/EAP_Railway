package com.farcasanutudorandrei.collections;

import com.farcasanutudorandrei.domain.Ticket;

import java.util.ArrayList;

public class TicketRepository implements GenericRepository<Ticket> {
    private ArrayList<Ticket> storage = new ArrayList<Ticket>();
    @Override
    public void add(Ticket entity) {
        storage.add(entity);
    }

    @Override
    public Ticket get(int id) {
        return storage.get(id);
    }

    @Override
    public void update(Ticket entityOld, Ticket entityNew) {
        storage.set(storage.indexOf(entityOld),entityNew);
    }

    @Override
    public void delete(Ticket entity) {
        storage.remove(entity);
    }

    @Override
    public int getSize() {
        return storage.size();
    }
}
