package org.tinygroup.tinyonlineservice;
import java.io.IOException;
import java.io.Reader;
import org.tinygroup.commons.tools.Resources;
import org.tinygroup.tinydb.DbOperatorFactory;
import org.tinygroup.tinydb.DbOperatorFactoryBuilder;

public class DbOperatorFactoryUtil {
	private final static DbOperatorFactory dbOperatorFactory;
	 static {
	 String resource = "tinydb.xml";
	 Reader reader = null;
	 try {
	 reader = Resources.getResourceAsReader(resource);
	 } catch (IOException e) {
	 System.out.println(e.getMessage());
	 }
	 dbOperatorFactory = new DbOperatorFactoryBuilder().build(reader);
	 }
	 public static DbOperatorFactory getSqlSessionFactory() {
	      return dbOperatorFactory;
	 }
}
