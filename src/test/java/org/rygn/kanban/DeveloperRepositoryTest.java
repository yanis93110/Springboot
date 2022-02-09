package org.rygn.kanban;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.rygn.kanban.dao.DeveloperRepository;
import org.rygn.kanban.domain.Developer;
import org.rygn.kanban.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
public class DeveloperRepositoryTest {

	@Autowired
    private DeveloperRepository developerRepository;
	
	@Test
	public void testFindAllDevelopers() {
		
		Collection<Developer> developers = this.developerRepository.findAll();
		
		Assert.assertEquals(1, developers.size());
	}
}
