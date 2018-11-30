package com.gxk.s2m.node;

public class StringNode extends Node{

  private final String val;

  public StringNode(String val) {
    this.val = val;
  }

  @Override
  public Object eval(Object... args) {
    return val;
  }
}
