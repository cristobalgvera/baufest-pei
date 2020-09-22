package com.baufest.tennis.springtennis.service;

import java.util.Collection;
import java.util.Optional;

public interface CrudMethods<Entity, ID> {
    Entity findById(ID id);

    Collection<Entity> findAll();

    Entity save(Entity entity);

    Entity update(Entity entity);

    void deleteById(ID id);
}
