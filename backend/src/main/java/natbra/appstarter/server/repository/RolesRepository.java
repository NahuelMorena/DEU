package natbra.appstarter.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import natbra.appstarter.server.model.auth.Role;

public interface RolesRepository extends JpaRepository<Role, Long> {

}
