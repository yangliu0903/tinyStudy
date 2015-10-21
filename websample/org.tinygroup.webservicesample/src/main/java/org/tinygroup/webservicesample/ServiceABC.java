/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (tinygroup@126.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.tinygroup.webservicesample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;

public class ServiceABC implements Serializable {
	private static Logger logger = LoggerFactory.getLogger(ServiceABC.class);

	public boolean readboolean(boolean a) {
		logger.logMessage(LogLevel.INFO, "read boolean");
		logger.logMessage(LogLevel.INFO, "value {}", a);
		return true;
	}

	public String readintString(int i, String s) {
		logger.logMessage(LogLevel.INFO, "read int s");
		logger.logMessage(LogLevel.INFO, "value {} {}", i, s);
		return s + "" + i;
	}
	
	public int readint(int i) {
		logger.logMessage(LogLevel.INFO, "read int");
		logger.logMessage(LogLevel.INFO, "value {}", i);
		return i*100;
	}

	public void readInterfaceUser(User2 user) {
		logger.logMessage(LogLevel.INFO, "read interface User");
		logger.logMessage(LogLevel.INFO, "{} {} {}", user, user.getName(),
				user.getAge());
	}

	public UserImpl readUser(UserImpl user) {
		logger.logMessage(LogLevel.INFO, "read UserImpl");
		logger.logMessage(LogLevel.INFO, "{} {} {}", user, user.getName(),
				user.getAge());
		UserImpl u = new UserImpl("a","12");
		return u;
	}

	public void readUserArray(UserImpl[] users) {
		logger.logMessage(LogLevel.INFO, "read UserImpl Array");
		logger.logMessage(LogLevel.INFO, "{}", users.length);
		for (UserImpl user : users) {
			logger.logMessage(LogLevel.INFO, "{} {} {}", user, user.getName(),
					user.getAge());
		}
	}

	public UserImpl[] writeUserArray() {
		UserImpl[] users = new UserImpl[2];
		users[0] = new UserImpl("a", "0");
		users[1] = new UserImpl("b", "1");
		logger.logMessage(LogLevel.INFO, "write UserImpl Array");
		logger.logMessage(LogLevel.INFO, "{}", users.length);
		for (UserImpl user : users) {
			logger.logMessage(LogLevel.INFO, "{} {} {}", user, user.getName(),
					user.getAge());
		}
		return users;
	}
	
	public UserImpl[] readWriteUserArray(UserImpl[] users) {
		logger.logMessage(LogLevel.INFO, "read UserImpl Array");
		logger.logMessage(LogLevel.INFO, "{}", users.length);
		for (UserImpl user : users) {
			logger.logMessage(LogLevel.INFO, "{} {} {}", user, user.getName(),
					user.getAge());
		}
		return users;
	}

	public void readUserList(List<UserImpl> users) {
		logger.logMessage(LogLevel.INFO, "read UserImpl List");
		logger.logMessage(LogLevel.INFO, "{}", users.size());
		for (UserImpl user : users) {
			logger.logMessage(LogLevel.INFO, "{} {} {}", user, user.getName(),
					user.getAge());
		}
	}

	public List<UserImpl> writeUserList() {
		List<UserImpl> users = new ArrayList<UserImpl>();
		users.add(new UserImpl("a", "0"));
		users.add(new UserImpl("b", "1"));
		logger.logMessage(LogLevel.INFO, "write UserImpl List");
		logger.logMessage(LogLevel.INFO, "{}", users.size());
		for (UserImpl user : users) {
			logger.logMessage(LogLevel.INFO, "{} {} {}", user, user.getName(),
					user.getAge());
		}
		return users;
	}
	
	public List<UserImpl> readWriteUserList(List<UserImpl> users) {
		logger.logMessage(LogLevel.INFO, "read UserImpl List");
		logger.logMessage(LogLevel.INFO, "{}", users.size());
		for (UserImpl user : users) {
			logger.logMessage(LogLevel.INFO, "{} {} {}", user, user.getName(),
					user.getAge());
		}
		return users;
	}

	public boolean[] readbooleanArray(boolean[] a){
		return a;
	}
	
	public int[] readintArray(int[] i){
		return i;
	}
	
	public String[] readStringArray(String[] s){
		return s;
	}
	
	public List<String> readStringList(List<String> s){
		return s;
	}
}
