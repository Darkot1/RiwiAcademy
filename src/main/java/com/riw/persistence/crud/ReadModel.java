package com.riw.persistence.crud;

public interface ReadModel <ID, Entity>{
    public Entity getEntity(ID id);
}
