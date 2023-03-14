/**
 * 
 */
package gg.fresher.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gg.fresher.demo.entities.Example;

/**
 * @created Mar 2, 2023
 *
 */
public interface ExampleJpaRepository extends JpaRepository<Example, Long> {

}
