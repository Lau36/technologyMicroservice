package com.example.microservice.technology.technology_microservice.domain.model;

import com.example.microservice.technology.technology_microservice.domain.utils.SortDirection;

public class PaginationModel {
    private int page;
    private int size;
    private SortDirection sortDirection;

    public PaginationModel(int page, int size, SortDirection sortDirection) {
        this.page = page;
        this.size = size;
        this.sortDirection = sortDirection;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }
}
