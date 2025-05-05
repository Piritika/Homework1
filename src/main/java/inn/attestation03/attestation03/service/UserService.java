package inn.attestation03.attestation03.service;

import inn.attestation03.attestation03.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    void createUser(UserDTO dto);
    void updateUser(Long id, UserDTO dto);
    List<UserDTO> getUsers();
    void softDeleteUser(Long id);
}
