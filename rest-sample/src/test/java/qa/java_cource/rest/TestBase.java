package qa.java_cource.rest;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;


public class TestBase {

  public Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  public boolean isIssueOpen(int issueId) throws IOException {
     String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    JsonObject first = issues.getAsJsonArray().getAsJsonObject(0);


    return true;
    //return (issues.getAs (0).getString("state_name") == "Open");
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
