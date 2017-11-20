/**
 * Copyright (c) 2017 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
package br.jus.trtsp.sustentacaoregional.taglines;

import br.jus.trtsp.sustentacaoregional.taglines.model.TagLine;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
