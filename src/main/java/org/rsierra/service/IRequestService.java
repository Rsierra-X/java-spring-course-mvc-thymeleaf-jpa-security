package org.rsierra.service;

import org.rsierra.models.Request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IRequestService {
    void saveRequest(Request request);
    void deleteRequest(Request request);
    List<Request> getAllRequests();
    Request getRequestById(int id);
    Page<Request> getAllRequests(Pageable pageable);
}
