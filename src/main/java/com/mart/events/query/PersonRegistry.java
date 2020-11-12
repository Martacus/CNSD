package com.mart.events.query;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRegistry extends CrudRepository<PersonData, UUID> {

}
