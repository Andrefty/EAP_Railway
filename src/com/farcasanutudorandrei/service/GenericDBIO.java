package com.farcasanutudorandrei.service;

import java.util.ArrayList;

public interface GenericDBIO <T>{
    void add (T collection);
    void load();
    T update(int id,String column,String value);
    void delete(int id);
}
