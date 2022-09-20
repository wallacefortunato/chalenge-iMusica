package challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import challenge.model.User;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> { //@Wallace_Fortunato: Alterada a classe de extensão para JpaRepository.

	User findByEmail(String email);
	
	boolean existsByEmail(String email);
	
}
