package fr.herman.memento.index;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Simple index structure</br>
 * addr = 8 Bytes * id
 *
 */
public class SimpleIndexStructure {
	private static final int UNIT_SIZE = 8;

	public long computeIndexPosition(long id) {
		return UNIT_SIZE * id;
	}

	public long readPosition(DataInput input) throws IOException {
		return input.readLong();
	}

	public void writePosition(DataOutput output, long position) throws IOException {
		output.writeLong(position);
	}
}
