package com.gxk.s2m;

import com.gxk.s2m.node.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class ParserTest {

  private Parser parser;

  @Before
  public void setup() {
    parser = new Parser();
  }

  @Test
  public void test_parse1() {
    String input = "select * from user where id = 93";

    List<Node> ret = parser.parse(input);

    Assert.assertTrue(ret.size() > 0);
  }

  @Test
  public void test_parse2() {
    String input = "select * from user where id = 93 order by id desc limit 1";

    List<Node> ret = parser.parse(input);

    Assert.assertTrue(ret.size() > 0);
  }

  @Test
  public void test_tokenizer() {
    String input = "select * from user where id = 93;";

    List<String> rets = parser.tokenizer(input);

    System.out.println(rets);

    Assert.assertTrue(rets.size() == 9);
    Assert.assertEquals("select", rets.get(0));
    Assert.assertEquals(";", rets.get(8));
  }
}