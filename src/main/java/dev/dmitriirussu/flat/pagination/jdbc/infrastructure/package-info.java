/**
 * JDBC implementation of the read repository.
 *
 * <p>All classes are intentionally kept in one package to leverage
 * package-private visibility as an encapsulation boundary.
 *
 * <p>Public API: {@link dev.dmitriirussu.flat.pagination.jdbc.infrastructure.JdbcOwnerReadRepository}
 *
 * <p>Internal (package-private by design):
 * {@link dev.dmitriirussu.flat.pagination.jdbc.infrastructure.OwnerProjection},
 * {@link dev.dmitriirussu.flat.pagination.jdbc.infrastructure.ViewMapper}
 */
package dev.dmitriirussu.flat.pagination.jdbc.infrastructure;