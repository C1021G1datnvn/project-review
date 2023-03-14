package gg.fresher.demo.utils;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;

/**
 * Paging dto
 * @author trandtb 
 */
@Data
public class Paging<T> {
    /**
     * total pages.
     */
    private long totalPages;

    /**
     * total elements.
     */
    private long totalElements;

    /**
     * page size.
     */
    private long pageSize;

    /**
     * page number.
     */
    private long pageNumber;

    /**
     * content.
     */
    private List<T> contents;

    /**
     * Instantiates a new paging.
     *
     * @author trandtb 
     * @param dtos the dtos
     */
    private Paging(List<T> dtos) {
        this.contents = dtos;
    }

    /**
     * Instantiates a new paging.
     *
     * @author trandtb 
     * @param page the page
     */
    private Paging(Page<T> page) {
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.pageSize = page.getSize();
        this.pageNumber = page.getNumber();
        this.contents = page.getContent();
    }

    /**
     * Instantiates a new paging.
     *
     * @author trandtb 
     * @param entities the entities
     * @param dtos     the dtos
     */
    private Paging(Page<?> entities, List<T> dtos) {
        this.totalPages = entities.getTotalPages();
        this.totalElements = entities.getTotalElements();
        this.pageSize = entities.getPageable().getPageSize();
        this.pageNumber = entities.getPageable().getPageNumber();
        this.contents = dtos;
    }

    /**
     * Of.
     *
     * @author trandtb 
     * @param <T>  the generic type
     * @param dtos the dtos
     * @return the paging
     */
    public static <T> Paging<T> of(List<T> dtos) {
        return new Paging<>(dtos);
    }

    /**
     * Of.
     *
     * @author trandtb 
     * @param <T>      the generic type
     * @param entities the entities
     * @return the paging
     */
    public static <T> Paging<T> of(Page<T> entities) {
        return new Paging<>(entities);
    }

    /**
     * Of.
     *
     * @author trandtb 
     * @param <T>      the generic type
     * @param entities the entities
     * @param dtos     the dtos
     * @return the paging
     */
    public static <T> Paging<T> of(Page<?> entities, List<T> dtos) {
        return new Paging<>(entities, dtos);
    }
}