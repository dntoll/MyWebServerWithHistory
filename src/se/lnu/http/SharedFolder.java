package se.lnu.http;

import java.io.File;
import java.io.FileNotFoundException;

public class SharedFolder {

	public SharedFolder(File sharedDirectory) {
		assert(sharedDirectory.isDirectory());
	}

	public File getURL(String string) throws FileNotFoundException {
		
		
		throw new FileNotFoundException();
	}

}
