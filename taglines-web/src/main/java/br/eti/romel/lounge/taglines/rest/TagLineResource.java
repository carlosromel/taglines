/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eti.romel.lounge.taglines.rest;

import br.eti.romel.lounge.taglines.*;
import javax.ejb.*;
import javax.enterprise.context.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * REST Web Service
 *
 * @author Carlos Romel Pereira da Silva <carlos.romel@gmail.com>
 */
@Path("tagline")
@RequestScoped
public class TagLineResource {

    @Context
    private UriInfo context;

    @EJB(mappedName = "java:global/taglines-ejb-1.0-SNAPSHOT/RandomTagLine!br.eti.romel.lounge.taglines.RandomTagLineRemote")
    private RandomTagLineRemote tag;

    /**
     * Creates a new instance of GenericResource
     */
    public TagLineResource() {
    }

    /**
     * Retrieves representation of an instance of
 br.eti.romel.lounge.taglines.rest.TagLineResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {

        return String.format("{ 'tagline': '%s'; }", tag.getRandomTagLine());
    }

    /**
     * PUT method for updating or creating an instance of TagLineResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
