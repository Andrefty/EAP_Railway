package com.farcasanutudorandrei.collections;

import java.util.ArrayList;

public interface GenericRepository <T> {

     void add(T entity);

     T get(int id);

     ArrayList<T> getAll();

     void update(T entityOld, T entityNew);

     void delete(T entity);

     int getSize();

     int getIndex(T entity);

}
