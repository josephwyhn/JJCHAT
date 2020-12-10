package com.example.jjchatapi.model;

public class GenericResult<E1, E2> {
    private E1 _entity1;
    private E2 _entity2;

    public GenericResult() {

    }

    public GenericResult(E1 entity1, E2 entity2){
        setEntity1(entity1);
        setEntity2(entity2);
    }

    public E1 getEntity1() {
        return _entity1;
    }

    public void setEntity1(E1 entity1) {
        _entity1 = entity1;
    }

    public E2 getEntity2() {
        return _entity2;
    }

    public void setEntity2(E2 entity2) {
        _entity2 = entity2;
    }
}