package gg.fresher.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gg.fresher.demo.entities.Country;
/**
 * @created Mar 13, 2023
 *
 */
public interface CountryJpaRepository extends JpaRepository<Country, Long>{

}
