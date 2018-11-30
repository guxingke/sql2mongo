package com.gxk.s2m.node;

import java.util.List;

public class SelectNode extends Node{

  private final String val;
  private final List<Node> body;

  public SelectNode(String val, List<Node> body) {
    this.val = val;
    this.body = body;
  }

  @Override
  public Object eval(Object... args) {
    if (body.size() < 3) {
      return "eval select err";
    }

    Node table = body.get(2);

    return String.format("db.%s.find", table.eval());
  }
}
