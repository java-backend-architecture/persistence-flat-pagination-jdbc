# persistence-flat-pagination-jdbc

A minimal JDBC example of offset-based pagination for flat entity queries — no Spring Data, pure SQL, clean architecture.

## What it shows

* Offset-based pagination with `LIMIT / OFFSET` and `COUNT(*)`
* Using `package-private` classes as an encapsulation boundary inside the infrastructure layer
* Separating persistence projections from application-level read models via `ViewMapper`

## Stack

* Java 25
* Spring Boot
* Spring JDBC (`JdbcClient`)
* H2 (in-memory database)

## Structure

```
application/
    OwnerReadRepository   ← port (interface)
    OwnerView             ← read model
    PageQuery             ← pagination input
    PageResult<T>         ← pagination output

infrastructure/
    JdbcOwnerReadRepository   ← JDBC implementation
    OwnerProjection           ← internal, never leaks out
    ViewMapper                ← projection → view
```

## How it works

```java
// page 0, 2 items per page
repository.findAllFlat(new PageQuery(0, 2));

// page 1, 2 items per page
repository.findAllFlat(new PageQuery(1, 2));
```

Output:

```
=== page - 0 ===
PageResult[content=[OwnerView[id=1, name=jack1], OwnerView[id=2, name=jack2]], page=0, size=2, total=10]

=== page - 1 ===
PageResult[content=[OwnerView[id=3, name=jack3], OwnerView[id=4, name=jack4]], page=1, size=2, total=10]
```

`PageQuery` and `PageResult` have zero framework dependencies — copy them into any Java project.

## Tests

Integration tests in `src/test/java` cover pagination correctness, last page with remainder, and edge cases including pages beyond total.

## Related

* [persistence-flat-pagination-sorting-jdbc](https://github.com/java-backend-architecture/persistence-flat-pagination-sorting-jdbc) — adds multi-field sorting with SQL injection protection
* [persistence-graph-pagination-jdbc](https://github.com/java-backend-architecture/persistence-graph-pagination-jdbc) — same approach over a multi-level object graph

## Run

```bash
./mvnw spring-boot:run
```
