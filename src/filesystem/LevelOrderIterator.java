package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;

import structures.Queue;

/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File>{
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */

	public File[] fileList; // array for subfiles 
	public Queue<File> file = new Queue<File>(); // general queue
	
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        	// TODO 1
		if (!rootNode.exists()) throw new FileNotFoundException();
		else {
			//Queue<File> file = new Queue<File>();
			file.enqueue(rootNode);
			fileList = null;
			}
			
		
	}
	
	@Override
	public boolean hasNext() {
        	// TODO 2
            return !file.isEmpty();
	}

	@Override
	public File next() throws NoSuchElementException {
        	// TODO 3
			if (!hasNext()) throw new NoSuchElementException();
			else {
				File old = file.peek();
				
				/*if (old.isDirectory())*/
				fileList = old.listFiles();
				
				
				
				if (fileList!=null) {
					Arrays.sort(fileList);
				for (File i: fileList)
					file.enqueue(i);
				}
				
				file.dequeue();
				return old;
			}
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
