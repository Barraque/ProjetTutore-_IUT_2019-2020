package fr.iut.projet.projettutorearchetype.repositories;

import fr.iut.projet.projettutorearchetype.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
}
