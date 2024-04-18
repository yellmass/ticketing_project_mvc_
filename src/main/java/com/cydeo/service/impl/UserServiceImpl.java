package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends AbstractMapService<UserDTO,String> implements UserService {
    @Override
    public UserDTO save(UserDTO userDTO) {
        return super.save(userDTO.getUserName(), userDTO);
    }

    @Override
    public UserDTO findById(String username) {
        return super.findById(username);
    }

    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String username) {
        super.deleteById(username);
    }

    @Override
    public void update(UserDTO userDTO) {
        super.update(userDTO.getUserName(), userDTO);
    }

    @Override
    public List<UserDTO> findManagers() {
        return super.findAll().stream()
                .filter(user->user.getRole().getId()==2L)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findEmployees() {
        return super.findAll().stream()
                .filter(user->user.getRole().getId()==3L)
                .collect(Collectors.toList());
    }
}
