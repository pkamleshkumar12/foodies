package com.ezcom.foodies.repository;

import com.ezcom.foodies.collection.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<Tag, String> {
}
