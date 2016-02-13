package fr.herman.memento.storage;

import java.io.IOException;
import java.io.RandomAccessFile;

import fr.herman.memento.io.DataReader;
import fr.herman.memento.io.DataWriter;

public class FileDataStore implements AutoCloseable {
	private final RandomAccessFile file;

	public FileDataStore(RandomAccessFile file) {
		this.file = file;
	}

	public <T> long writeRecord(long index, T data, DataWriter<T> writer) throws IOException {
		file.seek(index);
		long length = writer.write(data, file);
		return index + length;
	}

	public <T> T readRecord(long index, DataReader<T> reader) throws IOException {
		file.seek(index);
		return reader.read(file);
	}

	@Override
	public void close() throws Exception {
		file.close();
	}

}
