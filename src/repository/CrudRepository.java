package repository;

import entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrudRepository<T extends BaseEntity> {

    private List<T> data;

    public CrudRepository(List<T> data) {
        this.data = new ArrayList<>(data);
    }

    public List<T> findAll() {
        return new ArrayList<>(this.data);
    }

    public T save(T data) {
        this.data.add(data);
        return data;
    }

    public T findById(UUID id) {
        return this.data
                .stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public T findById(String id) {
        return findById(UUID.fromString(id));
    }

    public T update(T data) {
        int foundIndex = this.data.indexOf(data);
        if (foundIndex > -1) {
            this.data.set(foundIndex, data);
            return data;
        }
        return save(data);
    }

    public boolean delete(T data) {
        return this.data.removeIf(d -> d.equals(data));
    }

    public boolean deleteById(UUID id) {
        return this.data.removeIf(d -> d.getId().equals(id));
    }

    public boolean deleteById(String id) {
        UUID deleteId = UUID.fromString(id);
        return deleteById(deleteId);
    }

    public void printAll() {
        this.data.forEach(System.out::println);
    }
}
