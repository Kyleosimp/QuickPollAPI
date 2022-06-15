package com.apress.quickpoll4.repository;

import com.apress.quickpoll4.domain.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {
}
