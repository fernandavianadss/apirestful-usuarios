package br.com.fernandaviana.apirestful.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fernandaviana.apirestful.entities.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{

}
