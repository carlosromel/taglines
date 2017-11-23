/**
 * Copyright (c) 2017 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
package br.eti.romel.lounge.taglines;

import br.eti.romel.lounge.taglines.model.*;
import javax.ejb.*;
import javax.persistence.*;

/**
 *
 * @author Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
@Singleton
@Startup
public class RandomTagLine implements RandomTagLineRemote {

    @PersistenceContext
    private EntityManager em;

    /**
     * Obtém uma tagline aleatória.
     *
     * @return uma tagline aleatória.
     */
    @Override
    public String getRandomTagLine() {
        final String fairEnoughRandomTag = ""
                + " select t.tagline_id, t.tag, t.usage_count"
                + "  from tagline t"
                + " where t.usage_count = (select min(usage_count)"
                + "                          from tagline)"
                + "  order by random()"
                + "  limit 1";

        Query query = em.createNativeQuery(fairEnoughRandomTag, TagLine.class);

        TagLine tagLine = (TagLine) query.getSingleResult();

        tagLine.incrementUsageCount();
        em.persist(tagLine);

        return tagLine.getTag();
    }
}
