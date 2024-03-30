package com.example.codeE.model.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PaginationDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Pagination#Pagination()}
     *   <li>{@link Pagination#setPageNumber(int)}
     *   <li>{@link Pagination#setPageSize(int)}
     *   <li>{@link Pagination#setTotalPage(int)}
     *   <li>{@link Pagination#setTotalRecords(int)}
     *   <li>{@link Pagination#getPageNumber()}
     *   <li>{@link Pagination#getPageSize()}
     *   <li>{@link Pagination#getTotalPage()}
     *   <li>{@link Pagination#getTotalRecords()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Pagination actualPagination = new Pagination();
        actualPagination.setPageNumber(10);
        actualPagination.setPageSize(3);
        actualPagination.setTotalPage(1);
        actualPagination.setTotalRecords(1);
        int actualPageNumber = actualPagination.getPageNumber();
        int actualPageSize = actualPagination.getPageSize();
        int actualTotalPage = actualPagination.getTotalPage();

        // Assert that nothing has changed
        assertEquals(1, actualTotalPage);
        assertEquals(1, actualPagination.getTotalRecords());
        assertEquals(10, actualPageNumber);
        assertEquals(3, actualPageSize);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Pagination#Pagination(int, int, int, int)}
     *   <li>{@link Pagination#setPageNumber(int)}
     *   <li>{@link Pagination#setPageSize(int)}
     *   <li>{@link Pagination#setTotalPage(int)}
     *   <li>{@link Pagination#setTotalRecords(int)}
     *   <li>{@link Pagination#getPageNumber()}
     *   <li>{@link Pagination#getPageSize()}
     *   <li>{@link Pagination#getTotalPage()}
     *   <li>{@link Pagination#getTotalRecords()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        Pagination actualPagination = new Pagination(1, 3, 10, 1);
        actualPagination.setPageNumber(10);
        actualPagination.setPageSize(3);
        actualPagination.setTotalPage(1);
        actualPagination.setTotalRecords(1);
        int actualPageNumber = actualPagination.getPageNumber();
        int actualPageSize = actualPagination.getPageSize();
        int actualTotalPage = actualPagination.getTotalPage();

        // Assert that nothing has changed
        assertEquals(1, actualTotalPage);
        assertEquals(1, actualPagination.getTotalRecords());
        assertEquals(10, actualPageNumber);
        assertEquals(3, actualPageSize);
    }
}
