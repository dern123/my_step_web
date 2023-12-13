package server;

public record Item(int x, int y, String op, int result, String user_id) {

  @Override
  public String toString() {
    return "%d %s %d = %d : %s".formatted(x, op, y, result, user_id);
  }

  public String toHtml() {
    return new StringBuilder()
      .append("<tr>")
        .append("<td>")
        .append(x)
        .append("</td>")
        .append("<td>")
        .append(y)
        .append("</td>")
        .append("<td>")
        .append(op)
        .append("</td>")
        .append("<td>")
        .append(result)
        .append("</td>")
        .append("<td>")
        .append(user_id)
        .append("</td>")
      .append("</tr>")
      .toString();
  }

}
