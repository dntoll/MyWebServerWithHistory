package se.lnu.http;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class SharedFolderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRootURL() throws FileNotFoundException {
		
		File folder = mock(File.class);
		SharedFolder sut = new SharedFolder(folder);
		folder.listFiles()
		sut.getURL("/");
	}

}
