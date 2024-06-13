package org.rsierra.service.db;

import org.rsierra.models.Request;
import org.rsierra.repository.RequestRepository;
import org.rsierra.service.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceJpa implements IRequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public void saveRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public void deleteRequest(Integer id) {
        requestRepository.deleteById(id);
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public Request getRequestById(int id) {
        Optional<Request> request = requestRepository.findById(id);
        return request.orElse(null);
    }

    @Override
    public Page<Request> getAllRequests(Pageable pageable) {
        return requestRepository.findAll(pageable);
    }
}
