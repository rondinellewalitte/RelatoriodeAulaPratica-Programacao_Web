package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserRepository;
import services.exceptions.ResourceNotFoundException;
import entities.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (!user.isPresent()) {
                throw new ResourceNotFoundException("User with id " + id + " not found");
            }
            return user;
        } catch (Exception e) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
    }

    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new ResourceNotFoundException("User with id " + id + " not found");
            }
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
    }

    @Override
    public User update(User user) {
        try {
            if (!userRepository.existsById(user.getId())) {
                throw new ResourceNotFoundException("User with id " + user.getId() + " not found");
            }
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResourceNotFoundException("User with id " + user.getId() + " not found");
        }
    }
}
