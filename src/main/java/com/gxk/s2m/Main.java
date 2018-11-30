package com.gxk.s2m;

import com.gxk.s2m.node.Node;

import java.util.List;
import java.util.stream.Collectors;

public class Main{

  public static void main(String[] args){
    if (args.length < 1) {
      System.out.println("need sql");
      System.exit(-1);
    }

    System.out.print(new Main().eval(args[0]));
  }

  public String eval(String sql) {
    Parser parser = new Parser();
    List<Node> nodes = parser.parse(sql);

    return nodes.stream()
      .map(it -> it.eval())
      .map(String::valueOf)
      .collect(Collectors.joining());
  }
}
