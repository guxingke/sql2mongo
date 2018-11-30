package com.gxk.s2m.node;

import java.util.List;

public class SortNode extends Node{

  private final String val;
  private final List<Node> body;

  public SortNode(String val, List<Node> body) {
    this.val = val;
    this.body = body;
  }


  @Override
  public Object eval(Object... args) {
    return val;
  }
}
