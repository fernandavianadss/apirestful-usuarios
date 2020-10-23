package br.com.fernandaviana.apirestful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fernandaviana.apirestful.entities.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{

}
