package qa.java_cource.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.aspects.Immutable;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("f6704a57d2f07ebdf6782a31dc499b9b5e89ef52");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("AnnaBozsik", "Java_cource")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
