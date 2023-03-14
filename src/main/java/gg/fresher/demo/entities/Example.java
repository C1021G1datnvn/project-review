/**
 * 
 */
package gg.fresher.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @created Mar 2, 2023
 *
 */
@Entity
@Table(name = "example")
@Getter
@SuperBuilder
@NoArgsConstructor
public class Example extends AbstractAuditingEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "code", length = 100)
    private String code;

    @Column(name = "description", length = 500)
    private String description;
}
