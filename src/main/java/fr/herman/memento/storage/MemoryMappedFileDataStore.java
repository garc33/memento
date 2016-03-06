package fr.herman.memento.storage;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;

import fr.herman.memento.io.DataReader;
import fr.herman.memento.io.DataWriter;

public class MemoryMappedFileDataStore implements DataStore {
	private final RandomAccessFile file;
	private final MappedByteBuffer buffer;
	private final DataInputByteBuffer input;
	private final DataOutputByteBuffer output;

	public MemoryMappedFileDataStore(RandomAccessFile file) throws IOException {
		this.file = file;
		buffer = file.getChannel().map(MapMode.READ_WRITE, 0, Integer.MAX_VALUE);
		input = new DataInputByteBuffer(buffer);
		output = new DataOutputByteBuffer(buffer);
	}

	@Override
	public <T> long writeRecord(long index, T data, DataWriter<T> writer) throws IOException {
		output.seek(index);
		return writer.write(data, output);
	}

	@Override
	public <T> T readRecord(long index, DataReader<T> reader) throws IOException {
		input.seek(index);
		return reader.read(input);
	}

	@Override
	public void close() throws IOException {
		buffer.force();
		// file.close();
	}

}
