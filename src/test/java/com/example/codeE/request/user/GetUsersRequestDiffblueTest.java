package com.example.codeE.request.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class GetUsersRequestDiffblueTest {
    /**
     * Method under test:
     * {@link GetUsersRequest#GetUsersRequest(String, String, int, int)}
     */
    @Test
    void testNewGetUsersRequest() {
        // Arrange and Act
        GetUsersRequest actualGetUsersRequest = new GetUsersRequest("Role", "Search Keyword", 10, 3);

        // Assert
        assertEquals("Role", actualGetUsersRequest.getRole());
        assertEquals("Search Keyword", actualGetUsersRequest.getSearchKeyword());
        assertNull(actualGetUsersRequest.getPageable());
        assertEquals(10, actualGetUsersRequest.getPageNumber());
        assertEquals(3, actualGetUsersRequest.getPageSize());
    }
}
