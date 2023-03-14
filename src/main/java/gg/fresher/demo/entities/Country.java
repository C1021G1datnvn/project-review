package gg.fresher.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
/**
 * @created Mar 13, 2023
 *
 */
@Entity
@Table(name = "country")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Country extends AbstractAuditingEntity<Long>{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String code;
    
    @Column(name = "description", length = 500)
    private String description;
    
    @Column
    private Long continentId;
}
