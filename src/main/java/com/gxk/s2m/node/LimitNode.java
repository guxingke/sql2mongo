package com.gxk.s2m.node;

import java.util.List;

public class LimitNode extends Node{

  private final String val;
  private final List<Node> body;

  public LimitNode(String val, List<Node> body) {
    this.val = val;
    this.body = body;
  }

  @Override
  public Object eval(Object... args) {

    String eval = body.get(0).eval().toString();
    if (!eval.contains(",")) {
      return String.format(".limit(%s)", eval);
    }

    String[] split = eval.split(",");
    return String.format(".skip(%s).limit(%s)", split[0], split[1]);
  }
}
