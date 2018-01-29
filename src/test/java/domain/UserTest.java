package domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import mixin.UserMixin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.revinate.assertj.json.JsonPathAssert.assertThat;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User(123,
                "James",
                23,
                "Male",
                "james@gmail.com",
                "1234567890");
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void JacksonMixinAnnotationTest() throws JsonProcessingException {
        final ObjectMapper objectMapper = buildMapper();
        objectMapper.addMixIn(User.class, UserMixin.class);

        final String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        System.out.println(json);

        DocumentContext documentContext = JsonPath.parse(json);

        assertThat(documentContext).jsonPathAsInteger("$.id").isEqualTo(123);
        assertThat(documentContext).jsonPathAsString("$.name").isEqualTo("James");
        assertThat(documentContext).jsonPathAsInteger("$.age").isEqualTo(23);
        assertThat(documentContext).jsonPathAsString("$.gender").isEqualTo("Male");
        assertThat(documentContext).jsonPathAsString("$.email").isEqualTo("james@gmail.com");
        assertThat(documentContext).jsonPathAsString("$.phoneNo").isEqualTo("1234567890");
    }

    private static ObjectMapper buildMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibilityChecker(objectMapper.getSerializationConfig()
                .getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
        return objectMapper;

    }
}