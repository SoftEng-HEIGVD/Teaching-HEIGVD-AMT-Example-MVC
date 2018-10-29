package ch.heigvd.amt.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.probedock.client.annotations.ProbeTest;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import org.junit.Before;

/**
 * This class contains methods to test the REST API. We use Jersey Client to
 * facilitate the preparation of HTTP requests and the inspection of HTTP
 * responses (you will notice that Jersey Client provides a "fluent" API style).
 *
 * Note that on the server side, we use DTO classes and we let Jersey handle the
 * serialization and deserialization of payloads. We could have done the same
 * thing on the client side, but instead we have used the Jackson JSON library
 * and we deal with JSON trees ourselves. One reason for this choice is simply
 * that we wanted to provide an example and show what can be done.
 *
 * But another reason is to reduce the risk of introducing changes in the API
 * payloads. Indeed, if we wanted to write as little code as possible, we would
 * extract the DTO classes in a separate .jar file, which we would include both
 * in the server project and in the test project (via a maven dependency). This
 * way, when adding a property to a DTO, it would automatically be available on
 * the server and on the testing side. While this seems highly beneficial, it
 * means that the tests might sometimes fail to detect changes in the API. An
 * intermediate solution would be to have two separate DTO packages (one on
 * the server side and on the test side). In that case, if the DTO is changed
 * on the server side, then the DTO has to explicitely be changed on the test
 * side (and this is when the developer realizes that the API has changed, if
 * he was not aware yet).
 *
 * @author Olivier Liechti
 */
public class RestApiTest {

  public static final String MVCDEMO_API = "http://localhost:8080/MVCDemo-1.0-SNAPSHOT/api";

  Client client;

  ObjectMapper mapper;

  JsonNodeFactory factory;

  @Before
  public void setup() {
    client = ClientBuilder.newClient();
    mapper = new ObjectMapper();
    factory = new JsonNodeFactory(false);
  }

  @Test
  @ProbeTest(tags = "REST")
  public void sendingGetToNonExistingSectorShouldReturn404() throws IOException {
    /**
     * Generate a random sector name, so that we know for sure that there will
     * be no conflict when creating the new sector
     */
    long randomSectorId = System.currentTimeMillis();

    /**
     * Send the POST request with the JSON payload to create a new Sector
     */
    WebTarget target = client.target(MVCDEMO_API).path("sectors").path(Long.toString(randomSectorId));
    Response response = target.request().get();

    assertThat(response.getStatus()).isEqualTo(Response.Status.NOT_FOUND.getStatusCode());
  }

  @Test
  @ProbeTest(tags = "REST")
  public void itShouldBePossibleToCreateASector() throws IOException {
    /**
     * Generate a random sector name, so that we know for sure that there will
     * be no conflict when creating the new sector
     */
    String sectorName = getRandomUniqueName("Sector");
    JsonNode sectorNode = buildSectorNode(sectorName);

    /**
     * Send the POST request with the JSON payload to create a new Sector
     */
    WebTarget target = client.target(MVCDEMO_API).path("sectors");
    Response response = target.request().post(Entity.entity(sectorNode, "application/json"));

    /**
     * Check that we have received a 201 (CREATED) status code and that the
     * Location header is there.
     */
    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
    assertThat(response.getHeaderString("Location")).isNotNull();

    /**
     * Send a GET request to retrieve the entity that was just created. If the
     * Location header is correct and if the resource has been created, we can
     * validate its state compared to what we have sent before.
     */
    target = client.target(response.getHeaderString("Location"));
    response = target.request().get();
    String jsonPayload = response.readEntity(String.class);
    JsonNode root = mapper.readTree(jsonPayload);
    assertThat(root.get("name").asText()).isEqualTo(sectorName);
  }

  @Test
  @ProbeTest(tags = "REST")
  public void tryingToRecreateASectorShouldNotCreateADuplicate() throws IOException {
    /**
     * Generate a random sector name, so that we know for sure that there will
     * be no conflict when creating the new sector
     */
    String sectorName = getRandomUniqueName("Sector");
    JsonNode sectorNode = buildSectorNode(sectorName);

    /**
     * Send a first POST request with the JSON payload to create a new Sector
     */
    WebTarget target = client.target(MVCDEMO_API).path("sectors");
    Response response = target.request().post(Entity.entity(sectorNode, "application/json"));

    /**
     * Check that we have received a 201 (CREATED) status code and that the
     * Location header is there.
     */
    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
    String sectorUri = response.getHeaderString("Location");
    assertThat(sectorUri).isNotNull();

    /**
     * Send a second POST request with the same JSON payload to create a new
     * Sector
     */
    target = client.target(MVCDEMO_API).path("sectors");
    response = target.request().post(Entity.entity(sectorNode, "application/json"));

    /**
     * The sector was already defined, so it was not re-created. Check that we
     * have received a 200 (OK) status code and that the Location header
     * contains the Uri of the existing sector.
     */
    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
    String sectorUri2 = response.getHeaderString("Location");
    assertThat(sectorUri2).isEqualTo(sectorUri);
  }

  @Test
  @ProbeTest(tags = "REST")
  public void itShouldBePossibleToCreateACompany() throws IOException {
    /**
     * Generate random names for the company and for 2 sectors that we will
     * assign the company to.
     */

    String companyName = getRandomUniqueName("Company");
    String sector1Name = getRandomUniqueName("Sector");
    String sector2Name = getRandomUniqueName("Sector");

    String[] sectors = new String[2];
    sectors[0] = sector1Name;
    sectors[1] = sector2Name;
    JsonNode companyNode = buildCompanyNode(companyName, sectors);

    /**
     * Send the POST request with the JSON payload to create a new Company (this
     * will also create two sectors).
     */
    WebTarget target = client.target(MVCDEMO_API).path("companies");
    Response response = target.request().post(Entity.entity(companyNode, "application/json"));

    /**
     * Check that we have received a 201 (CREATED) status code and that the
     * Location header is there.
     */
    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
    assertThat(response.getHeaderString("Location")).isNotNull();

    /**
     * Send a GET request to retrieve the entity that was just created. If the
     * Location header is correct and if the resource has been created, we can
     * validate its state compared to what we have sent before. We also check
     * for default values.
     */
    target = client.target(response.getHeaderString("Location"));
    response = target.request().get();
    String jsonPayload = response.readEntity(String.class);
    JsonNode root = mapper.readTree(jsonPayload);

    assertThat(root.get("name").asText()).isEqualTo(companyName);
    assertThat(root.get("ceo").asText()).isEqualTo("There is no CEO");
    assertThat(root.get("numberOfEmployees").asLong()).isEqualTo(0);
    List<JsonNode> v1 = root.findValues("sectors");
    List<JsonNode> v2 = companyNode.findValues("sectors");

    assertThat(v1).containsExactlyElementsOf(v2);

  }

  @Test
  @ProbeTest(tags = "REST")
  public void itShouldBePossibleToListCompanies() throws IOException {
    WebTarget target = client.target(MVCDEMO_API).path("companies");
    Response response = target.request().get();
    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());

    String jsonPayload = response.readEntity(String.class);
    assertThat(jsonPayload).isNotNull();
    assertThat(jsonPayload).isNotEmpty();

    JsonNode[] rootNodeasArray = mapper.readValue(jsonPayload, JsonNode[].class);
    assertThat(rootNodeasArray).isNotNull();
    assertThat(rootNodeasArray.length).isNotEqualTo(0);

    for (JsonNode company : rootNodeasArray) {
      assertThat(company.get("ceo")).isNotNull();
      assertThat(company.get("sectors")).isNotNull();
      assertThat(company.get("numberOfEmployees")).isNotNull();
    }
  }

  private long namesCounter = 1;

  @Test
  @ProbeTest(tags = "REST")
  public void itShouldBePossibleToHireAnEmployee() throws IOException {
    /**
     * Firstly, create a company
     */
    String companyName = getRandomUniqueName("Company");
    String[] sectors = new String[0];
    JsonNode companyNode = buildCompanyNode(companyName, sectors);
    WebTarget target = client.target(MVCDEMO_API).path("companies");
    Response response = target.request().post(Entity.entity(companyNode, "application/json"));
    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
    String companyUri = response.getHeaderString("Location");

    /**
     * Secondly, submit a hiring form
     */
    String firstName = getRandomUniqueName("FirstName");
    String lastName = getRandomUniqueName("LastName");
    String title = "staff member";
    JsonNode hiringFormNode = buildHiringFormNode(firstName, lastName, title);
    target = client.target(companyUri).path("hiringForms");
    response = target.request().post(Entity.entity(hiringFormNode, "application/json"));
    assertThat(response.getStatus()).isEqualTo(Response.Status.CREATED.getStatusCode());
    String employeeUri = response.getHeaderString("Location");

    /**
     * Thirdly, check that the employee is listed in the company
     */
    target = client.target(companyUri).path("employees");
    response = target.request().get();
    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
    String jsonPayload = response.readEntity(String.class);
    JsonNode[] rootAsArray = mapper.readValue(jsonPayload, JsonNode[].class);
    assertThat(rootAsArray).isNotNull();
    assertThat(rootAsArray.length).isEqualTo(1);

    for (JsonNode employee : rootAsArray) {
      assertThat(employee.get("firstName").asText()).isEqualTo(firstName);
      assertThat(employee.get("lastName").asText()).isEqualTo(lastName);
      assertThat(employee.get("title").asText()).isEqualTo(title);
    }

    /**
     * Check that the employee count on the company resource
     */
    target = client.target(companyUri);
    response = target.request().get();
    assertThat(response.getStatus()).isEqualTo(Response.Status.OK.getStatusCode());
    jsonPayload = response.readEntity(String.class);
    JsonNode root = mapper.readValue(jsonPayload, JsonNode.class);
    assertThat(root.get("name").asText()).isEqualTo(companyName);
    assertThat(root.get("numberOfEmployees").asLong()).isEqualTo(1);

  }

  private String getRandomUniqueName(String prefix) {
    namesCounter++;
    return prefix + "-" + System.currentTimeMillis() + "-" + namesCounter;
  }

  private ObjectNode buildCompanyNode(String companyName, String[] sectorNames) {
    ObjectNode companyNode = factory.objectNode();
    companyNode.put("name", companyName);

    ArrayNode sectorsNode = factory.arrayNode();
    for (String sectorName : sectorNames) {
      sectorsNode.add(sectorName);
    }
    companyNode.put("sectors", sectorsNode);
    return companyNode;
  }

  private ObjectNode buildSectorNode(String sectorName) {
    ObjectNode sector = factory.objectNode();
    sector.put("name", sectorName);
    return sector;
  }

  private ObjectNode buildHiringFormNode(String firstName, String lastName, String title) {
    ObjectNode hiringFormNode = factory.objectNode();
    hiringFormNode.put("firstName", firstName);
    hiringFormNode.put("lastName", lastName);
    hiringFormNode.put("title", title);
    return hiringFormNode;
  }

}
