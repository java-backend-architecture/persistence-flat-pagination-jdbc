package dev.dmitriirussu.flat.pagination.jdbc.infrastructure;

import dev.dmitriirussu.flat.pagination.jdbc.application.OwnerView;

/**
 * Maps persistence projections to application read models.
 */
final class ViewMapper {

    private ViewMapper() {}

    public static OwnerView toView(OwnerProjection owner) {
        return new OwnerView(owner.id(), owner.name());
    }
}
