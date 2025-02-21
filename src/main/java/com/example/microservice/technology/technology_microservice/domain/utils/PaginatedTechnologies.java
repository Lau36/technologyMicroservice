package com.example.microservice.technology.technology_microservice.domain.utils;

import com.example.microservice.technology.technology_microservice.domain.model.TechnologyModel;

import java.util.List;

public class PaginatedTechnologies {

    public List<TechnologyModel> getTechnologies() {
        return technologies;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTechnologies(List<TechnologyModel> technologies) {
        this.technologies = technologies;
    }

    public PaginatedTechnologies(List<TechnologyModel> technologies, int currentPage, Long totalElements, int totalPages) {
        this.technologies = technologies;
        this.currentPage = currentPage;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    private List<TechnologyModel> technologies;
    private int currentPage;
    private Long totalElements;
    private int totalPages;
}
