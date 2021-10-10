package io.nanovc.technologies.unirest;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * http://kong.github.io/unirest-java/
 */
public class UniRESTTests
{
    /**
     * http://kong.github.io/unirest-java/#requests
     */
    @Test
    public void requests()
    {
        HttpResponse<String> response = Unirest.get("http://google.com/search")
            .queryString("q", "unirest-java")
            .asString();

        String result = "OK";
        assertEquals(result, response.getStatusText());
    }
}
