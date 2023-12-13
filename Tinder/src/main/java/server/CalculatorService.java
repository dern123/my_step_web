package server;

import java.util.Optional;

public class CalculatorService {

  int parseToInt(String s) {
    return Integer.parseInt(s);
  }

  Optional<Operation> parseOp(String s) {
    Operation op = switch (s) {
      case "add" -> new Operation((x, y) -> x + y, "+");
      case "sub" -> new Operation((x, y) -> x - y, "-");
      case "mul" -> new Operation((x, y) -> x * y, "*");
      case "div" -> new Operation((x, y) -> x / y, "/");
      default -> null;
    };
    return Optional.ofNullable(op);
  }

  public Optional<Item> doOp(String xs, String ys, String ops, String user_id) {
    int x = parseToInt(xs);
    int y = parseToInt(ys);
    Optional<Operation> maybeFunc = parseOp(ops);

    return maybeFunc
        .map(op -> {
          int z = op.f().apply(x, y);
          return new Item(x, y, op.show(), z, user_id);
        });
  }
}
