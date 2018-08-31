package io.github.rafaelzomer.template;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateRunner {
  private static String BASE_PATH = "./src/main/resources/template/";
  private static String BASE_TEMPLATE;
  private static String CONTAINER_TEMPLATE;

  static {
    try {
      BASE_TEMPLATE = getFile(BASE_PATH + "base.html");
      CONTAINER_TEMPLATE = getFile(BASE_PATH + "container.html");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static String getFile(String path) throws IOException {
    return new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
  }

  public static TemplateBuilder create(HttpServletResponse response) throws IOException {
    return new TemplateBuilder(BASE_TEMPLATE, response);
  }

  public static TemplateBuilder container(HttpServletResponse response) throws IOException {
    return new TemplateBuilder(CONTAINER_TEMPLATE, response);
  }

  public static class TemplateBuilder {
    public static final String CONTENT = "{CONTENT}";
    public static final String TITLE = "{TITLE}";
    private final HttpServletResponse response;
    private final PrintWriter out;
    private String base;
    private List<String> content = new ArrayList<>();
    private Map<String, String> variables = new HashMap<>();

    TemplateBuilder(String base, HttpServletResponse response) throws IOException {
      this.base = base;
      this.response = response;
      this.out = response.getWriter();
    }

    public TemplateBuilder menu() throws IOException {
      return template("menu");
    }

    public TemplateBuilder template(String path) throws IOException {
      String fileContent = getFile("./src/main/resources/template/" + path + ".html");
      content.add(fileContent);
      return this;
    }

    public TemplateBuilder file(String path) throws IOException {
      String fileContent = getFile("./src/main/resources/view/" + path + ".html");
      content.add(fileContent);
      return this;
    }
    public TemplateBuilder variable(String key, String value) {
      variables.put(key, value);
      return this;
    }

    public TemplateBuilder component(TemplateBuilder templateBuilder) {
      content.add(templateBuilder.build());
      return this;
    }

    public TemplateBuilder content(String value) {
      content.add(value);
      return this;
    }

    public TemplateBuilder title(String title) {
      base = base.replace(TITLE, title);
      return this;
    }

    public String build() {
      for (String value : content) {
        base = base.replace(CONTENT, value + CONTENT);
      }
      for (Map.Entry<String, String> entry : variables.entrySet())
      {
        base = base.replace("{" + entry.getKey()  + "}", entry.getValue() != null ? entry.getValue() : "");
      }
      base = base.replace(CONTENT, "");
      return base;
    }

    public void render() {
      response.setContentType("text/html; charset=UTF-8");
      response.setCharacterEncoding("UTF-8");
      out.write(build());
    }
  }
}
