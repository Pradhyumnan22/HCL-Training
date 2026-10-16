package com.hcl.training.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class GenericRepository<T, ID> {
    private final List<T> entities = new ArrayList<>();
    private final Function<T, ID> idExtractor;

    public GenericRepository(Function<T, ID> idExtractor) { this.idExtractor = idExtractor; }
    public void save(T entity) { entities.add(entity); }
    public Optional<T> findById(ID id) { return entities.stream().filter(e -> idExtractor.apply(e).equals(id)).findFirst(); }
    public List<T> findAll() { return List.copyOf(entities); }
}
