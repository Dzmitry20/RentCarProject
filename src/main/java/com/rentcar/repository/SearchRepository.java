package com.rentcar.repository;

import com.rentcar.controller.requests.SearchRequest;
import com.rentcar.domain.User;

import java.util.List;

public interface SearchRepository {

    List<User> criteriaApiSearch(SearchRequest request);
}
