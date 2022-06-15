package com.apress.quickpoll4.repository;

import com.apress.quickpoll4.domain.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {
}
