/**
 * Copyright (c) 2017 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
package br.jus.trtsp.sustentacaoregional.taglinesweb;

import br.jus.trtsp.sustentacaoregional.taglines.RandomTagLineRemote;
import javax.ejb.EJB;
import javax.inject.Named;

/**
 *
 * @author Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
@Named
public class TagLineBean {

    @EJB(mappedName = "java:global/taglines-ejb-1.0-SNAPSHOT/RandomTagLine!br.jus.trtsp.sustentacaoregional.taglines.RandomTagLineRemote")
    private RandomTagLineRemote tag;

    public String getTagLine() {
        return tag.getRandomTagLine();
    }
}
