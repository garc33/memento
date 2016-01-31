package fr.herman.memento.storage;

import java.io.InputStream;

public interface StorageWriter {
	long writeData(long cursor, InputStream data);
}
