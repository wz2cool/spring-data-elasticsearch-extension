package com.github.wz2cool.elasticsearch.repository;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public abstract class ElasticsearchExtensionRepository<T, I> {

    private ElasticsearchRepository<T, I> elasticsearchRepository;

    public ElasticsearchExtensionRepository(ElasticsearchRepository<T, I> elasticsearchRepository) {
        this.elasticsearchRepository = elasticsearchRepository;
    }


    /// region proxy methods

    public <S extends T> S index(S var1) {
        return this.elasticsearchRepository.index(var1);
    }

    public <S extends T> S indexWithoutRefresh(S var1) {
        return this.elasticsearchRepository.indexWithoutRefresh(var1);
    }

    public Iterable<T> search(QueryBuilder var1) {
        return this.elasticsearchRepository.search(var1);
    }

    public Page<T> search(QueryBuilder var1, Pageable var2) {
        return this.elasticsearchRepository.search(var1, var2);
    }

    public Page<T> search(SearchQuery var1) {
        return this.elasticsearchRepository.search(var1);
    }

    public Page<T> searchSimilar(T var1, String[] var2, Pageable var3) {
        return this.elasticsearchRepository.searchSimilar(var1, var2, var3);
    }

    public void refresh() {
        this.elasticsearchRepository.refresh();
    }

    public Class<T> getEntityClass() {
        return this.elasticsearchRepository.getEntityClass();
    }

    /// endregion
}
