package dev.dmitriirussu.flat.pagination.jdbc.application;

/**
 * Read-only repository for querying owner data.
 *
 * <p>Defined in the application layer as a port —
 * infrastructure provides the implementation.
 */
public interface OwnerReadRepository {
    PageResult<OwnerView> findAllFlat(PageRequest request);
}
