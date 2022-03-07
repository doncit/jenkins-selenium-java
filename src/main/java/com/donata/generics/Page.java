package com.donata.generics;

import java.util.List;

public class Page<T> {

    private List<T> items;
    private boolean hasNext;
    private boolean hasPrevious;
    private int pageNumber;
    private int pageSize;
    private int totalItems;
    private int totalPages;

    public Page(List<T> items) {
        this.items = items;
    }
}
