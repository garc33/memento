package fr.herman.memento;

import java.io.IOException;

import fr.herman.memento.index.Index;
import fr.herman.memento.schema.Schema;
import fr.herman.memento.sequence.Sequence;
import fr.herman.memento.storage.DataStore;

public class Database<T> implements AutoCloseable {

	private final DataStore store;
	private final Index index;
	private final Sequence sequence;
	private final Schema<T> schema;
	private long cursor = 0;

	public Database(DataStore store, Index index, Sequence sequence, Schema<T> schema) {
		this.store = store;
		this.index = index;
		this.sequence = sequence;
		this.schema = schema;
	}

	public long insert(T data) throws IOException {
		long id = sequence.nextValue();
		long length = store.writeRecord(cursor, data, schema);
		index.setPosition(id, cursor);
		cursor += length;
		return id;
	}

	public void update(long id, T data) throws IOException {
		long length = store.writeRecord(0, data, schema);
		index.setPosition(id, cursor);
		cursor += length;
	}

	public T get(long id) throws IOException {
		long position = index.getPosition(id);
		return store.readRecord(position, schema);
	}

	@Override
	public void close() throws Exception {
		System.out.println(cursor);
		try {
			store.close();
			index.close();
		} finally {
		}
	}

}
