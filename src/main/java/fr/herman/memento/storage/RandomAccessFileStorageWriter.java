package fr.herman.memento.storage;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class RandomAccessFileStorageWriter implements StorageWriter, Closeable {

	private RandomAccessFile file;

	private byte[] buffer;

	public RandomAccessFileStorageWriter(File dataFile, int bufferSize) throws FileNotFoundException {
		file = new RandomAccessFile(dataFile, "rwd");
		buffer = new byte[bufferSize];
	}

	@Override
	public long writeData(long cursor, InputStream data) {
		try {
			int length = 0;
			if (file.getFilePointer() != cursor)
				file.seek(cursor);
			int current = 0;
			while ((current = data.read(buffer)) != -1) {
				file.write(buffer, 0, current);
				length += current;
			}
			return cursor + length;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() throws IOException {
		file.close();
	}

}
