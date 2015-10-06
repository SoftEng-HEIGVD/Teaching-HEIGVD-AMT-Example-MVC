package ch.heigvd.amt.mvcdemo.rest.resources;

import ch.heigvd.amt.mvcdemo.model.entities.Sector;
import ch.heigvd.amt.mvcdemo.rest.dto.SectorDTO;
import ch.heigvd.amt.mvcdemo.services.dao.BusinessDomainEntityNotFoundException;
import ch.heigvd.amt.mvcdemo.services.dao.SectorsDAOLocal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Stateless
@Path("/sectors")
public class SectorsResource {

  @Context
  UriInfo uriInfo;

  @EJB
  private SectorsDAOLocal sectorsDAO;

  @GET
  @Produces("application/json")
  public List<SectorDTO> getSectors() {
    List<SectorDTO> result = new ArrayList<>();
    List<Sector> sectors = sectorsDAO.findAll();
    for (Sector sector : sectors) {
      long sectorId = sector.getId();

      URI sectorHref = uriInfo
        .getAbsolutePathBuilder()
        .path(SectorsResource.class, "getSector")
        .build(sectorId);

      SectorDTO dto = new SectorDTO();
      dto.setHref(sectorHref);
      dto.setName(sector.getName());
      result.add(dto);
    }
    return result;
  }

  @POST
  @Consumes("application/json")
  public Response createSector(SectorDTO sectorDTO) {
    boolean created;
    long sectorId;
    try {
      sectorId = sectorsDAO.findByName(sectorDTO.getName()).getId();
      created = false;
    } catch (BusinessDomainEntityNotFoundException ex) {
      created = true;
      sectorId = sectorsDAO.create(new Sector(sectorDTO.getName()));
    }

    URI sectorUri = uriInfo
      .getBaseUriBuilder()
      .path(SectorsResource.class)
      .path(SectorsResource.class, "getSector")
      .build(sectorId);

    ResponseBuilder builder;
    if (created) {
      builder = Response.created(sectorUri);
    } else {
      builder = Response.ok().location(sectorUri);
    }
    
    return builder.build();
  }

  @GET
  @Path("/{id}")
  @Produces("application/json")
  public Sector getSector(@PathParam(value = "id") long id) throws BusinessDomainEntityNotFoundException {
    return sectorsDAO.findById(id);
  }

  @PUT
  @Path("/{id}")
  @Consumes("application/json")
  public Response updateSector(SectorDTO sectorDTO, @PathParam(value = "id") long id) throws BusinessDomainEntityNotFoundException {
    Sector sector = sectorsDAO.findById(id);
    sector.setName(sectorDTO.getName());
    return Response.ok().build();
  }

  @DELETE
  @Path("/{id}")
  public Response deleteSector(@PathParam(value = "id") long id) throws BusinessDomainEntityNotFoundException {
    Sector sector = sectorsDAO.findById(id);
    sectorsDAO.delete(sector);
    return Response.ok().build();
  }

}
