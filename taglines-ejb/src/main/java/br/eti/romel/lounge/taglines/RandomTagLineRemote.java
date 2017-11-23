/**
 * Copyright (c) 2017 Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
package br.eti.romel.lounge.taglines;

import javax.ejb.Remote;

/**
 *
 * @author Carlos Romel Pereira da Silva, carlos.romel@gmail.com
 */
@Remote
public interface RandomTagLineRemote {

    String getRandomTagLine();
}
