# persistence-flat-pagination-jdbc

A minimal JDBC example of offset-based pagination for flat entity queries — no Spring Data, pure SQL, clean architecture.

## What's inside

- `PageRequest` — page index (zero-based) and page size
- `PageResult<T>` — content + pagination metadata, framework-agnostic, with a `.map()` for type transformations
- `OwnerReadRepository` — port in the application layer
- `JdbcOwnerReadRepository` — JDBC implementation with `LIMIT / OFFSET` and `COUNT(*)`
- `OwnerProjection` and `ViewMapper` — package-private, never leak outside infrastructure

## How it works

```java
// page 0, 2 items per page
repository.findAllFlat(new PageRequest(0, 2));

// page 1, 2 items per page
repository.findAllFlat(new PageRequest(1, 2));
```

Output:

```
=== page - 0 ===
PageResult[content=[OwnerView[id=1, name=jack1], OwnerView[id=2, name=jack2]], page=0, size=2, total=10]

=== page - 1 ===
PageResult[content=[OwnerView[id=3, name=jack3], OwnerView[id=4, name=jack4]], page=1, size=2, total=10]
```

## Architecture

```
application/
  OwnerReadRepository   ← port (interface)
  OwnerView             ← read model
  PageRequest           ← pagination input
  PageResult<T>         ← pagination output

infrastructure/
  JdbcOwnerReadRepository   ← JDBC implementation
  OwnerProjection           ← internal, never leaks out
  ViewMapper                ← projection → view
```

`PageRequest` and `PageResult` have zero framework dependencies — copy them into any Java project.

## Next

[persistence-flat-pagination-sorting-jdbc](https://github.com/java-backend-architecture/persistence-flat-pagination-sorting-jdbc) — adds multi-field sorting with SQL injection protection.
