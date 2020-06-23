package fr.iut.projet.projettutorearchetype.repositories;

import fr.iut.projet.projettutorearchetype.models.RolesEnum;
import fr.iut.projet.projettutorearchetype.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String s);
    Boolean existsByLogin(String login);
    List<User> findAllByFirstnameContainsAndLastnameContainsAndLoginContainsAndDepartmentNumber_DepartmentId(String fname, String lname, String uname, int departmentId);
    List<User> findAllByFirstnameContainsAndLastnameContainsAndLoginContainsAndRole(String fname, String lname, String uname, RolesEnum role);
    List<User> findAllByFirstnameContainsAndLastnameContainsAndLoginContainsAndRoleAndDepartmentNumber_DepartmentId(String fname, String lname, String uname, RolesEnum role, int departmentId);
    List<User> findAllByFirstnameContainsAndLastnameContainsAndLoginContains(String fname, String lname, String uname);
}
