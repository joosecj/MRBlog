package com.mr.blog.services;

import com.mr.blog.dto.UserDTO;
import com.mr.blog.entities.User;
import com.mr.blog.repositories.UserRepository;
import com.mr.blog.services.exeptions.DataBaseException;
import com.mr.blog.services.exeptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
        User userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return new UserDTO(userEntity);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(UserDTO::new);
    }

    @Transactional(readOnly = false)
    public UserDTO insert(UserDTO userDTO) {
        try {
            User userEntity = new User();
            copyDtoToEntity(userDTO, userEntity);
            userRepository.save(userEntity);
            return new UserDTO(userEntity);
        } catch (ConstraintViolationException e) {
            throw new ResourceNotFoundException("E-mail já cadastrado");
        }
    }

    @Transactional(readOnly = false)
    public UserDTO update(Long id, UserDTO userDTO) {
        try {
            User userEntity = userRepository.getReferenceById(id);
            copyDtoToEntity(userDTO, userEntity);
            return new UserDTO(userRepository.save(userEntity));
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade refencial");
        }
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
