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

    assertEquals("db.user.find({\"_id\": {\"$eq\": NumberLong(93)}}).limit(1)", ret);
  }

  @Test
  public void test_e_1() {
    String sql = "select * from recUser where fromUserId = 93";

    String ret = main.eval(sql);

    assertTrue(ret.contains("fromUserId"));
  }

  @Test
  public void test_gt() {

    String sql = "select * from user where id > 93 limit 1";

    String ret = main.eval(sql);

    assertEquals("db.user.find({\"_id\": {\"$gt\": NumberLong(93)}}).limit(1)", ret);
  }

  @Test
  public void test_gte() {

    String sql = "select * from user where id >= 93 limit 1";

    String ret = main.eval(sql);

    assertEquals("db.user.find({\"_id\": {\"$gte\": NumberLong(93)}}).limit(1)", ret);
  }

  @Test
  public void test_order_default() {

    String sql = "select * from user where id >= 93 order by id limit 1";

    String ret = main.eval(sql);

    assertEquals("db.user.find({\"_id\": {\"$gte\": NumberLong(93)}}).sort({\"_id\": -1}).limit(1)", ret);
  }

  @Test
  public void test_order_desc() {

    String sql = "select * from user where id >= 93 order by id desc limit 1";

    String ret = main.eval(sql);

    assertEquals("db.user.find({\"_id\": {\"$gte\": NumberLong(93)}}).sort({\"_id\": -1}).limit(1)", ret);
  }

  @Test
  public void test_order_asc() {

    String sql = "select * from user where id >= 93 order by id asc limit 1";

    String ret = main.eval(sql);

    assertEquals("db.user.find({\"_id\": {\"$gte\": NumberLong(93)}}).sort({\"_id\": 1}).limit(1)", ret);
  }
}