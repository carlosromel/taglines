/*
 * Copyleft (c) 2017 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
package br.eti.romel.lounge.taglines.model;

import java.io.*;
import javax.persistence.*;
import lombok.*;

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
