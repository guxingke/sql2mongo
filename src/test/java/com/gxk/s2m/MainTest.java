package com.gxk.s2m;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

  Main main;

  @Before
  public void setup() {
    main = new Main();
  }

  @Test
  public void eval() {
    String sql = "select * from user where id = 93 limit 1";

    String ret = main.eval(sql);

    assertEquals("db.user.find({\"_id\": NumberLong(93)}).limit(1)", ret);
  }

  @Test
  public void test_e_1() {
    String sql = "select * from recUser where fromUserId = 93";

    String ret = main.eval(sql);

    assertTrue(ret.contains("fromUserId"));
  }
}