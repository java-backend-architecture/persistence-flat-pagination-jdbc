package dev.dmitriirussu.flat.pagination.jdbc.infrastructure;

import dev.dmitriirussu.flat.pagination.jdbc.application.PageRequest;
import dev.dmitriirussu.flat.pagination.jdbc.application.PageResult;
import dev.dmitriirussu.flat.pagination.jdbc.application.OwnerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcOwnerReadRepositoryTest {

    @Autowired
    JdbcClient jdbc;

    JdbcOwnerReadRepository repository;

    @BeforeEach
    void setUp() {
        repository = new JdbcOwnerReadRepository(jdbc);
    }

    @Test
    void findAllFlat_returnsRequestedPage() {
        PageResult<OwnerView> result = repository.findAllFlat(new PageRequest(0, 2));

        assertThat(result.content()).hasSize(2);
        assertThat(result.page()).isEqualTo(0);
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.total()).isEqualTo(10);
    }

    @Test
    void findAllFlat_returnsCorrectContent() {
        PageResult<OwnerView> result = repository.findAllFlat(new PageRequest(0, 2));

        assertThat(result.content().get(0).name()).isEqualTo("jack1");
        assertThat(result.content().get(1).name()).isEqualTo("jack2");
    }

    @Test
    void findAllFlat_returnsNextPage() {
        PageResult<OwnerView> result = repository.findAllFlat(new PageRequest(1, 2));

        assertThat(result.content()).hasSize(2);
        assertThat(result.content().get(0).name()).isEqualTo("jack3");
        assertThat(result.content().get(1).name()).isEqualTo("jack4");
    }

    @Test
    void findAllFlat_returnsLastPage_withRemainder() {
        PageResult<OwnerView> result = repository.findAllFlat(new PageRequest(3, 3));

        assertThat(result.content()).hasSize(1);
        assertThat(result.content().get(0).name()).isEqualTo("jack10");
        assertThat(result.total()).isEqualTo(10);
    }

    @Test
    void findAllFlat_returnsEmptyContent_whenPageBeyondTotal() {
        PageResult<OwnerView> result = repository.findAllFlat(new PageRequest(99, 10));

        assertThat(result.content()).isEmpty();
        assertThat(result.total()).isEqualTo(10);
    }

    @Test
    void findAllFlat_totalIsAlwaysCorrect_regardlessOfPage() {
        PageResult<OwnerView> page0 = repository.findAllFlat(new PageRequest(0, 3));
        PageResult<OwnerView> page1 = repository.findAllFlat(new PageRequest(1, 3));

        assertThat(page0.total()).isEqualTo(10);
        assertThat(page1.total()).isEqualTo(10);
    }
}
