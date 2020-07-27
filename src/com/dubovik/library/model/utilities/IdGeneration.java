package com.dubovik.library.model.utilities;

public class IdGeneration {
    private static int id = 1;

    public IdGeneration(){
    }

    public int generateId(){
        return id++;
    }
}
