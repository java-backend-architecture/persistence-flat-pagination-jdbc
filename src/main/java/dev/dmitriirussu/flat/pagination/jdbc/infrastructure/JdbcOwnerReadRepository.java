package dev.dmitriirussu.flat.pagination.jdbc.infrastructure;

import dev.dmitriirussu.flat.pagination.jdbc.application.OwnerReadRepository;
import dev.dmitriirussu.flat.pagination.jdbc.application.OwnerView;
import dev.dmitriirussu.flat.pagination.jdbc.application.PageRequest;
import dev.dmitriirussu.flat.pagination.jdbc.application.PageResult;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JDBC implementation of {@link OwnerReadRepository}.
 */
@Repository
public class JdbcOwnerReadRepository implements OwnerReadRepository {

    private final JdbcClient jdbc;

    JdbcOwnerReadRepository(JdbcClient jdbc) { this.jdbc = jdbc; }

    /** SQL queries for owner read operations. */
    private interface Sql {

        String SELECT_PAGE = """
            SELECT o.id AS owner_id,
                   o.name AS owner_name
            FROM owners o
            ORDER BY o.id
            LIMIT :limit OFFSET :offset
            """;

        String COUNT_ALL = """
            SELECT COUNT(*)
            FROM owners
            """;
    }

    public PageResult<OwnerView> findAllFlat(PageRequest request) {

        int offset = request.page() * request.size();

        List<OwnerView> content = jdbc.sql(Sql.SELECT_PAGE)
                .param("limit", request.size())
                .param("offset", offset)
                .query(OwnerProjection.class)
                .stream()
                .map(ViewMapper::toView)
                .toList();

        Long total = jdbc.sql(Sql.COUNT_ALL).query(Long.class).single();

        return new PageResult<>(
                content,
                request.page(),
                request.size(),
                total
        );
    }
}
