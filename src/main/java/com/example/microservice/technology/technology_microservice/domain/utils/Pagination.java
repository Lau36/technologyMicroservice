package com.example.microservice.technology.technology_microservice.domain.utils;

public class Pagination {
    private int page;
    private int size;
    private SortDirection sortDirection;

    public Pagination(int page, int size, SortDirection sortDirection) {
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
