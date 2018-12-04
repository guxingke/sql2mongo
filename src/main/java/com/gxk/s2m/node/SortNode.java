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
//    "order by id desc"

    if (body.size() < 1) {
      return "";
    }

    if (body.size() == 1) {
      String key = body.get(0).eval().toString();
      if (key.equals("id")) {
        key = "_id";
      }

      return String.format(".sort({\"%s\": -1})", key);
    }

    if (body.size() == 2) {
      String key = body.get(0).eval().toString();
      if (key.equals("id")) {
        key = "_id";
      }

      int seq = -1;
      String val = body.get(1).eval().toString();
      if (val.equals("asc")) {
        seq = 1;
      }

      return String.format(".sort({\"%s\": %d})", key, seq);
    }

    return "";
  }
}
