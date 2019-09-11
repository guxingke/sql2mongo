package com.gxk.s2m;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import com.gxk.s2m.select.SelectLexer;
import com.gxk.s2m.select.SelectParser;

public class Main {

  public static void main(String[] args) {
    String exp = Arrays.stream(args).collect(Collectors.joining(" "));

    if (exp.trim().startsWith("show")) {
      // show case
      if (exp.endsWith("tables")) {
        System.out.println("db.getCollectionNames()");
        return;
      }
      return;
    }

    CharStream input = new ANTLRInputStream(exp + "\n");
    SelectLexer lexer = new SelectLexer(input);
    CommonTokenStream stream = new CommonTokenStream(lexer);
    SelectParser parser = new SelectParser(stream);
    ParserRuleContext ctx = parser.stat();
    String code = new EvalVisitor().visit(ctx);
    System.out.println(code);
  }
}
