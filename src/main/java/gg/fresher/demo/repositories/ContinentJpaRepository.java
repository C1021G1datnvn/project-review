package gg.fresher.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gg.fresher.demo.entities.Continent;

public interface ContinentJpaRepository extends JpaRepository<Continent, Long>{

}
