package com.farcasanutudorandrei.service;

import java.util.ArrayList;

public interface GenericCSVIO <T> {

    void add(String filename,T collection);

    ArrayList<T> load(String filename);


}