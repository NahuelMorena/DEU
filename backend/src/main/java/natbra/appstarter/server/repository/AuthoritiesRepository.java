package natbra.appstarter.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import natbra.appstarter.server.model.auth.Authority;

public interface AuthoritiesRepository extends JpaRepository<Authority, Long> {

}
