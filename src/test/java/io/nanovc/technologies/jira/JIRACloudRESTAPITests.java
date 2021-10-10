package io.nanovc.technologies.jira;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for the JIRA Cloud REST API.
 * https://developer.atlassian.com/cloud/jira/platform/rest/v3/
 */
public class JIRACloudRESTAPITests
{
    /**
     * This is the base URL for the JIRA instance that we want to connect to.
     * https://JIRA_BASE_URL.atlassian.net
     */
    private static final String JIRA_BASE_URL = System.getenv("JIRA_BASE_URL");

    /**
     * The email address of the users token being used.
     */
    private static final String JIRA_API_EMAIL = System.getenv("JIRA_API_EMAIL");

    /**
     * The API token of the user being used.
     */
    private static final String JIRA_API_TOKEN = System.getenv("JIRA_API_TOKEN");

    /**
     * https://developer.atlassian.com/cloud/jira/platform/rest/v3/api-group-issues/#api-rest-api-3-events-get
     */
    @Test
    public void getEvents()
    {
        HttpResponse<JsonNode> response = getJIRA_JSON("events");
        String json = """
            [
              {
                "id": 1,
                "name": "Issue Created"
              },
              {
                "id": 2,
                "name": "Issue Updated"
              },
              {
                "id": 3,
                "name": "Issue Assigned"
              },
              {
                "id": 4,
                "name": "Issue Resolved"
              },
              {
                "id": 5,
                "name": "Issue Closed"
              },
              {
                "id": 6,
                "name": "Issue Commented"
              },
              {
                "id": 14,
                "name": "Issue Comment Edited"
              },
              {
                "id": 17,
                "name": "Issue Comment Deleted"
              },
              {
                "id": 7,
                "name": "Issue Reopened"
              },
              {
                "id": 8,
                "name": "Issue Deleted"
              },
              {
                "id": 9,
                "name": "Issue Moved"
              },
              {
                "id": 10,
                "name": "Work Logged On Issue"
              },
              {
                "id": 11,
                "name": "Work Started On Issue"
              },
              {
                "id": 12,
                "name": "Work Stopped On Issue"
              },
              {
                "id": 15,
                "name": "Issue Worklog Updated"
              },
              {
                "id": 16,
                "name": "Issue Worklog Deleted"
              },
              {
                "id": 13,
                "name": "Generic Event"
              }
            ]""";
        assertEquals(json, response.getBody().toPrettyString());
    }

    /**
     * https://developer.atlassian.com/cloud/jira/platform/rest/v3/api-group-instance-information/#api-rest-api-3-instance-license-get
     */
    @Test
    public void getInstanceLicense()
    {
        HttpResponse<JsonNode> response = getJIRA_JSON("instance/license");
        String json = """
            {
              "applications": [
                {
                  "id": "jira-software",
                  "plan": "PAID"
                },
                {
                  "id": "jira-core",
                  "plan": "PAID"
                }
              ]
            }""";
        assertEquals(json, response.getBody().toPrettyString());
    }

    /**
     * Makes a GET request to JIRA with the given API URL and returns the JSON response.
     *
     * @param apiURL The specific sub-section of the JIRA API URL that we want to invoke.
     * @return The JSON response from JIRA.
     */
    public HttpResponse<JsonNode> getJIRA_JSON(String apiURL)
    {
        // This code sample uses the  'Unirest' library:
        // http://unirest.io/java.html
        HttpResponse<JsonNode> response = Unirest.get("https://" + JIRA_BASE_URL + ".atlassian.net/rest/api/3/" + apiURL)
            .basicAuth(JIRA_API_EMAIL, JIRA_API_TOKEN)
            .header("Accept", "application/json")
            .asJson();

        return response;
    }
}
