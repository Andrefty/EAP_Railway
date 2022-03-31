package com.farcasanutudorandrei.collections;

public interface GenericRepository <T> {

     void add(T entity);

     T get(int id);

     void update(T entityOld, T entityNew);

     void delete(T entity);

     int getSize();

}
