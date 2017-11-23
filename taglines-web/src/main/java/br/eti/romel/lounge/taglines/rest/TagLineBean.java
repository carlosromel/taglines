/**
 * Copyright (c) 2017 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
package br.eti.romel.lounge.taglines.rest;

import br.eti.romel.lounge.taglines.*;
import javax.ejb.*;
import javax.inject.*;

/**
 *
 * @author Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
@Named
public class TagLineBean {

    @EJB(mappedName = "java:global/taglines-ejb-1.0-SNAPSHOT/RandomTagLine!br.eti.romel.lounge.taglines.RandomTagLineRemote")
    private RandomTagLineRemote tag;

    public String getTagLine() {

        return tag.getRandomTagLine();
    }
}
