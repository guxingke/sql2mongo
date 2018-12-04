package com.gxk.s2m.node;

import java.util.List;

public class WhereNode extends Node{

  private final String val;
  private final List<Node> body;

  public WhereNode(String val, List<Node> body) {
    this.val = val;
    this.body = body;
  }

  @Override
  public Object eval(Object... args) {
    int size = body.size();
    boolean sizeValid = size % 4 == 3;
    if (!sizeValid) {
      return "node where eval err";
    }

    // just support all and, or all or.
    if (size == 3) {
      switch (body.get(1).eval().toString()) {
        case "=":
          return eval("$eq", body.get(0).eval().toString(), body.get(2).eval());
        case "!=":
          return eval("$ne", body.get(0).eval().toString(), body.get(2).eval());
        case ">":
          return eval("$gt", body.get(0).eval().toString(), body.get(2).eval());
        case ">=":
          return eval("$gte", body.get(0).eval().toString(), body.get(2).eval());
        case "<":
          return eval("$lt", body.get(0).eval().toString(), body.get(2).eval());
        case "<=":
          return eval("$lte", body.get(0).eval().toString(), body.get(2).eval());
      }
    }

    return "future ...";
  }

  private String eval(String op, String key, Object val) {
    if (key.equals("id")) {
      key = "_id";
    }
    String newVal = val.toString();

    try {
      val = Long.parseLong(newVal);
    } catch (Exception e) {
      // ignore
    }

    if (val instanceof Number) {
      newVal = String.format("NumberLong(%d)", val);
    }

    if (key.equals("_id") && val instanceof String) {
      newVal = String.format("ObjectId(\"%s\")", val);
    }

    return String.format("({\"%s\": {\"%s\": %s}})", key, op, newVal);
  }
}
