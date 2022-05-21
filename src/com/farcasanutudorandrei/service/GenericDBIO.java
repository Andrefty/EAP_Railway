package com.farcasanutudorandrei.service;

import java.util.ArrayList;

public interface GenericDBIO <T>{
    void add (T collection);
    void load();
}
