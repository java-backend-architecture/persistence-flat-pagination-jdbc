package dev.dmitriirussu.flat.pagination.jdbc.application;

import java.util.List;
import java.util.function.Function;

/**
 * Generic page result wrapper.
 *
 * @param <T> type of content in page
 */
public record PageResult<T>(List<T> content, int page, int size, long total) {

    /**
     * Maps page content to another type while preserving pagination metadata.
     *
     * @param mapper function to transform elements
     * @param <R>    target type
     * @return mapped page result
     */
    public <R> PageResult<R> map(Function<T, R> mapper) {
        return new PageResult<>(
                content.stream().map(mapper).toList(),
                page,
                size,
                total
        );
    }
}
