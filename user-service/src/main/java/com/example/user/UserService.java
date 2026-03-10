package com.example.user;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class UserService {
    private ConcurrentHashMap<Long, User> userStore = new ConcurrentHashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    public List<User> getAllUsers() {
        return new ArrayList<>(userStore.values());
    }

    public User getUser(Long id) {
        return userStore.get(id);
    }

    public User createUser(User user) {
        Long id = idGenerator.getAndIncrement();
        user.setId(id);
        userStore.put(id, user);
        return user;
    }

    public User updateUser(User user) {
        if (userStore.containsKey(user.getId())) {
            userStore.put(user.getId(), user);
            return user;
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        return userStore.remove(id) != null;
    }
}