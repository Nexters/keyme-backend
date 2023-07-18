package com.nexters.keyme.dummy;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DummyUserRepository {
    public List<DummyUser> findAllByUserId(List<Long> userIds) {
        return new ArrayList<>();
    }
}
