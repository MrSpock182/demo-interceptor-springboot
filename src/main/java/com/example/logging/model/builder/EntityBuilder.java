package com.example.logging.model.builder;

import com.example.logging.model.Entity;

public final class EntityBuilder {
    private String name;

    private EntityBuilder() {
    }

    public static EntityBuilder builder() {
        return new EntityBuilder();
    }

    public EntityBuilder name(String name) {
        this.name = name;
        return this;
    }

    public Entity build() {
        Entity entity = new Entity();
        entity.setName(name);
        return entity;
    }
}
