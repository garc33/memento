package fr.herman.memento.storage;

import java.io.Reader;

public interface StorageWriter {
	long writeData(long cursor,Reader data);
}
