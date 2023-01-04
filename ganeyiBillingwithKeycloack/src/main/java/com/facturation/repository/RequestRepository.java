package com.facturation.repository;

import com.facturation.domain.Request;
import com.facturation.domain.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data MongoDB repository for the Request entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestRepository extends MongoRepository<Request, String> {
     List<Request> findByRequestDateBetweenAndClient(LocalDate startDate, LocalDate endDate, Client client);
}
