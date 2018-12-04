package com.gxk.s2m;

import com.gxk.s2m.node.LimitNode;
import com.gxk.s2m.node.Node;
import com.gxk.s2m.node.SelectNode;
import com.gxk.s2m.node.SortNode;
import com.gxk.s2m.node.StringNode;
import com.gxk.s2m.node.WhereNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

  public List<Node> parse(String input) {
    List<String> tokens = this.tokenizer(input);

    Reader reader = new Reader(tokens);

    List<Node> nodes = parseSql(reader);

    return nodes;
  }

  private List<Node> parseSql(Reader reader) {
    List<Node> rets = new ArrayList<>();
    while (reader.peek() != null) {
      String token = reader.peek();
      switch (token) {
        case "select":
          rets.add(new SelectNode(reader.next(), readSelect(reader)));
          continue;
        case "where":
          rets.add(new WhereNode(reader.next(), readWhere(reader)));
          continue;
        case "order by":
          rets.add(new SortNode(reader.next(), readSort(reader)));
          continue;
        case "limit":
          rets.add(new LimitNode(reader.next(), readLimit(reader)));
          continue;
        default:
          return readDefault(reader);
      }
    }

    return rets;
  }

  private List<Node> readDefault(Reader reader) {
    return Arrays.asList(new StringNode(reader.next()));
  }

  private List<Node> readLimit(Reader reader) {
    List<Node> rets = new ArrayList<>();
    String token = reader.peek();

    while (!(token == null)) {
      rets.addAll(readDefault(reader));

      token = reader.peek();
    }
    return rets;
  }

  private List<Node> readSort(Reader reader) {
    List<Node> rets = new ArrayList<>();
    String token = reader.peek();

    List<String> terminators = Arrays.asList("limit");
    while (!(token == null || terminators.contains(token))) {
      rets.addAll(readDefault(reader));

      token = reader.peek();
    }
    return rets;
  }

  private List<Node> readWhere(Reader reader) {
    List<Node> rets = new ArrayList<>();
    String token = reader.peek();

    List<String> terminators = Arrays.asList("order by", "limit");
    while (!(token == null || terminators.contains(token))) {
      rets.addAll(readDefault(reader));

      token = reader.peek();
    }
    return rets;
  }

  private List<Node> readSelect(Reader reader) {
    List<Node> rets = new ArrayList<>();
    String token = reader.peek();

    List<String> terminators = Arrays.asList("where", "order by", "limit");
    while (!(token == null || terminators.contains(token))) {
      rets.addAll(readDefault(reader));

      token = reader.peek();
    }
    return rets;
  }


  public List<String> tokenizer(String val) {
    List<String> tokens = new ArrayList<>();
    Pattern pattern = Pattern.compile("([*;])|(select | from | where | order by | desc | asc | != | = | > | >= | < | <= )|\\'\\S*\\'|(\\w)+");
    Matcher matcher = pattern.matcher(val);

    while (matcher.find()) {
      String token = matcher.group();
      if (token != null &&
        !token.equals("")) {
        tokens.add(token.trim());
      }
    }

    return tokens;
  }

  static class Reader {
    List<String> tokens;
    int position;

    Reader(List<String> tokens) {
      this.tokens = tokens;
      this.position = 0;
    }

    String next() {
      if (position == tokens.size()) {
        return null;
      }
      String token = tokens.get(position);
      position++;
      return token;
    }

    String peek() {
      if (position == tokens.size()) {
        return null;
      }
      return tokens.get(position);
    }
  }
}
