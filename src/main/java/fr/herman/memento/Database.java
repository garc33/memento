package fr.herman.memento;

import java.io.IOException;

import fr.herman.memento.index.Index;
import fr.herman.memento.schema.Schema;
import fr.herman.memento.sequence.Sequence;
import fr.herman.memento.storage.FileDataStore;

public class Database<T> implements AutoCloseable {

	private final FileDataStore store;
	private final Index index;
	private final Sequence sequence;
	private final Schema<T> schema;

	public Database(FileDataStore store, Index index, Sequence sequence, Schema<T> schema) {
		this.store = store;
		this.index = index;
		this.sequence = sequence;
		this.schema = schema;
	}

	public long insert(T data) throws IOException {
		long id = sequence.nextValue();
		long position = store.writeRecord(0, data, schema);
		index.setPosition(id, position);
		return id;
	}

	public void update(long id, T data) throws IOException {
		long position = store.writeRecord(0, data, schema);
		index.setPosition(id, position);
	}

	public T get(long id) throws IOException {
		long position = index.getPosition(id);
		return store.readRecord(position, schema);
	}

	@Override
	public void close() throws Exception {
		try {
			index.close();
		} finally {
			store.close();
		}
	}

}
