package com.rentcar.service;

import com.rentcar.controller.requests.UserCreateRequest;
import com.rentcar.controller.requests.UserRequest;
import com.rentcar.controller.requests.mappers.UserCreateMapper;
import com.rentcar.controller.requests.mappers.UserMapper;
import com.rentcar.domain.Role;
import com.rentcar.domain.User;
import com.rentcar.exception.NoSuchEntityException;
import com.rentcar.exception.UserNotFoundException;
import com.rentcar.repository.RoleRepository;
import com.rentcar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final RoleService roleService;

    private final UserMapper userMapper;

    private final UserCreateMapper userCreateMapper;


    public Page<User> findAllUsers() {
        return userRepository.findAll(PageRequest.of(1, 10, Sort.by(Sort.Direction.DESC, "id")));
    }


    public UserRequest updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("User not found by id " + id));

        userMapper.updateUserFromUserRequest(userRequest, user);
        user.setName(userRequest.getName());
        user.setSurname(userRequest.getSurname());
        user.setGender(userRequest.getGender());
        user.setLogin(userRequest.getLogin());
        user.setPhone(userRequest.getPhone());
        user.setPassportSeries(userRequest.getPassportSeries());
        user.setPassportNumber(userRequest.getPassportNumber());
        user.setEmail(userRequest.getEmail());
        user.setDriverLicenseNumber(user.getDriverLicenseNumber());

        UserRequest request = new UserRequest();
        user = userRepository.save(user);
        userMapper.updateUserRequestFromUser(user, request);

        return request;
    }

    public List<UserRequest> findUserByName(String name) {
        List<User> users = userRepository.findByNameContainingIgnoreCase(name);
        users.removeIf(User::getIsDeleted);
        if (users.isEmpty()) {
            throw new NoSuchEntityException("There is no users with similar names");
        }
        return wrap(users);
    }

    private List<UserRequest> wrap(List<User> users) {
        List<UserRequest> result = new ArrayList<>();
        for (User user : users) {
            UserRequest request = new UserRequest();
            userMapper.updateUserRequestFromUser(user, request);
            result.add(request);
        }
        return result;
    }

    public List<UserRequest> findUserByNameAndSurname(String name, String surname) {
        List<User> users = userRepository.findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(name,surname);
        users.removeIf(User::getIsDeleted);
        if (users.isEmpty()) {
            throw new NoSuchEntityException("There is no users with similar names");
        }
        return wrap(users);
    }

    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    public User findByLogin(String login) {
        if (existsByLogin(login)) {
            return userRepository.findByLogin(login);
        } else {
            throw new UserNotFoundException(login);
        }
    }

    public List<UserRequest> findAllExistingUsers() {
        List<User> notDeletedUsers = userRepository.findByIsDeletedFalse();
        return wrap(notDeletedUsers);
    }

    public UserRequest findOneUser(Long id) {
        User notDeletedUsers = userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new NoSuchEntityException("User not found by id " + id));
        UserRequest request = new UserRequest();
        userMapper.updateUserRequestFromUser(notDeletedUsers,request);
        return request ;
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public UserRequest createUser(UserCreateRequest userCreateRequest) {
        User user = new User();
        userCreateMapper.updateUserFromUserCreateRequest(userCreateRequest, user);
        user.setIsDeleted(false);
        user = userRepository.save(user);
        roleService.addUserForRole(1L, user.getId());
        UserRequest request = new UserRequest();
        userMapper.updateUserRequestFromUser(user, request);
        return request;
    }




}
