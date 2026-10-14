package com.hcl.training.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class InMemoryRepository<T, ID> implements CrudRepository<T, ID> {
    private final List<T> entities = new ArrayList<>();
    private final Function<T, ID> idExtractor;

    public InMemoryRepository(Function<T, ID> idExtractor) {
        this.idExtractor = idExtractor;
    }

    @Override
    public void save(T entity) { entities.add(entity); }

    @Override
    public Optional<T> findById(ID id) {
        return entities.stream().filter(entity -> idExtractor.apply(entity).equals(id)).findFirst();
    }

    @Override
    public List<T> findAll() { return List.copyOf(entities); }
}
