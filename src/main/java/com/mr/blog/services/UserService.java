package com.mr.blog.services;

import com.mr.blog.dto.UserDTO;
import com.mr.blog.entities.User;
import com.mr.blog.repositories.UserRepository;
import com.mr.blog.services.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO findById(long id) {
       User userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        return new UserDTO(userEntity);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(UserDTO::new);
    }

    @Transactional(readOnly = false)
    public UserDTO insetUser(UserDTO userDTO) {
        User userEntity = new User();
        copyDtoToEntity(userDTO, userEntity);
        userRepository.save(userEntity);
        return new UserDTO(userEntity);
    }

    @Transactional(readOnly = false)
    public UserDTO update(Long id, UserDTO userDTO) {
        User userEntity = userRepository.getReferenceById(id);
        copyDtoToEntity(userDTO, userEntity);
        return new UserDTO(userRepository.save(userEntity));
    }
    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    private void copyDtoToEntity(UserDTO userDTO, User userEntity) {
        LocalDate today = LocalDate.now();
        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setBirthDate(userDTO.getBirthDate());
        userEntity.setUrlImage(userDTO.getUrlImage());
        userEntity.setRegistrationDate(today);
    }
}
