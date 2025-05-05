package inn.attestation03.attestation03.service.impl;

import inn.attestation03.attestation03.model.dto.UserDTO;
import inn.attestation03.attestation03.model.entity.User;
import inn.attestation03.attestation03.repository.UserRepository;
import inn.attestation03.attestation03.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void createUser(UserDTO dto) {
        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .softDeleted(false)
                .build();

        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        userRepository.save(user);
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream()
                .filter(user -> !user.isSoftDeleted())
                .map(user -> UserDTO.builder()
                        .id(user.getId())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .age(user.getAge())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void softDeleteUser(Long id) {
        userRepository.findById(id).ifPresentOrElse(user -> {
            user.setSoftDeleted(true);
            userRepository.save(user);
        }, () -> {
            throw new RuntimeException("User not found");
        });
    }
}
