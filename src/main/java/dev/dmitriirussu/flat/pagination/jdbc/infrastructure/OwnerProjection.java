package dev.dmitriirussu.flat.pagination.jdbc.infrastructure;

/**
 * Persistence read model for entity extraction.
 */
class OwnerProjection {
    private final Long id;
    private final String name;

    private OwnerProjection(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    static OwnerProjection of(Long id, String name) {
        return new OwnerProjection(id, name);
    }

    Long id() { return id; }
    String name() { return name; }
}
