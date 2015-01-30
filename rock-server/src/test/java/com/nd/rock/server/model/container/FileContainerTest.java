package com.nd.rock.server.model.container;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nd.rock.server.model.container.impl.SnapshotFileContainer;
import com.nd.rock.server.model.dao.CoreDataDAO;

public class FileContainerTest {
	
	@Test
	public void init(){
		try {
			ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					"application-context.xml");
			CoreDataDAO coreDataDAO = (CoreDataDAO) applicationContext.getBean("coreDataDAO");
			
			SnapshotFileContainer snapshotFileContainer = new SnapshotFileContainer();
			snapshotFileContainer.setCoreDataDAO(coreDataDAO);
			snapshotFileContainer.init();
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
