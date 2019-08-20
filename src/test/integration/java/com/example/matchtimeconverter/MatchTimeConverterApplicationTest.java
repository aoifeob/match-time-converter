package com.example.matchtimeconverter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchTimeConverterApplicationTest {

  @Autowired
  ApplicationContext ctx;

  @Test
  public void run() throws Exception {
    CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
    runner.run("src/test/resources/test.data");
  }
}
