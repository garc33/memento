package fr.herman.memento.index;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;

import fr.herman.memento.storage.DataInputByteBuffer;
import fr.herman.memento.storage.DataOutputByteBuffer;

/**
 * Simple implementation of index using a {@link RandomAccessFile} to store
 * positions in store.</br>
 */
public class MemoryMappedIndex implements Index {

	private final SimpleIndexStructure structure;
	private final MappedByteBuffer buffer;
	private final DataInputByteBuffer input;
	private final DataOutputByteBuffer output;
	private boolean read = false;

	public MemoryMappedIndex(RandomAccessFile file, SimpleIndexStructure structure) throws IOException {
		buffer = file.getChannel().map(MapMode.READ_WRITE, 0, Integer.MAX_VALUE);
		input = new DataInputByteBuffer(buffer);
		output = new DataOutputByteBuffer(buffer);
		this.structure = structure;
	}

	@Override
	public long getPosition(long id) throws IOException {
		// if (!read) {
		// read = true;
		// buffer.flip();
		// }
		buffer.position((int) structure.computeIndexPosition(id));
		return structure.readPosition(input);
	}

	@Override
	public void setPosition(long id, long position) throws IOException {
		// if (read) {
		// read = false;
		// buffer.flip();
		// }
		buffer.position((int) structure.computeIndexPosition(id));
		structure.writePosition(output, position);
	}

	@Override
	public void close() throws Exception {
		buffer.force();
	}

}
