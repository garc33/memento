package fr.herman.memento.index;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Simple implementation of index using a {@link RandomAccessFile} to store
 * positions in store.</br>
 */
public class RandomAccessIndex implements Index {

	private final RandomAccessFile file;

	private final SimpleIndexStructure structure;

	public RandomAccessIndex(RandomAccessFile file, SimpleIndexStructure structure) {
		this.file = file;
		this.structure = structure;
	}

	@Override
	public long getPosition(long id) throws IOException {
		file.seek(structure.computeIndexPosition(id));
		return structure.readPosition(file);
	}

	@Override
	public void setPosition(long id, long position) throws IOException {
		file.seek(structure.computeIndexPosition(id));
		structure.writePosition(file, position);
	}

	@Override
	public void close() throws Exception {
		file.close();
	}

}
