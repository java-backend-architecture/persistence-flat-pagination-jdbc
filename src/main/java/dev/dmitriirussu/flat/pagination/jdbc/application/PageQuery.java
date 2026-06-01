package dev.dmitriirussu.flat.pagination.jdbc.application;

/**
 * Pagination request model.
 *
 * @param page zero-based page index
 * @param size number of items per page
 */
public record PageQuery(int page, int size) {}
