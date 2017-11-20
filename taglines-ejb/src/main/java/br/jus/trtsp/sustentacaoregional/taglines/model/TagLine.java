/*
 * Copyleft (c) 2017 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
package br.jus.trtsp.sustentacaoregional.taglines.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagLine implements Serializable {

    @Transient
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tagline_id")
    private Long id;

    private String tag;

    @Column(name = "usage_count")
    private Integer usageCount;

    public void incrementUsageCount() {
        ++this.usageCount;
    }
}
