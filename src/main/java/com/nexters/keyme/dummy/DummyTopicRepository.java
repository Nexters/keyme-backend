package com.nexters.keyme.dummy;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DummyTopicRepository {
    public List<DummyTopic> findAllByTopicId(List<Long> ids) {
        return new ArrayList<>();
    }
}
