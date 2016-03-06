package fr.herman.memento.storage;

import java.io.DataInput;
import java.io.IOException;
import java.nio.ByteBuffer;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DataInputByteBuffer implements DataInput {

	private final ByteBuffer buffer;

	public DataInputByteBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void readFully(byte[] b) throws IOException {
		buffer.get(b);
	}

	@Override
	public void readFully(byte[] b, int off, int len) throws IOException {
		buffer.get(b, off, len);
	}

	@Override
	public int skipBytes(int n) throws IOException {
		throw new NotImplementedException();
	}

	@Override
	public boolean readBoolean() throws IOException {
		throw new NotImplementedException();
	}

	@Override
	public byte readByte() throws IOException {
		return buffer.get();
	}

	@Override
	public int readUnsignedByte() throws IOException {
		throw new NotImplementedException();
	}

	@Override
	public short readShort() throws IOException {
		return buffer.getShort();
	}

	@Override
	public int readUnsignedShort() throws IOException {
		return buffer.getShort();
	}

	@Override
	public char readChar() throws IOException {
		return buffer.getChar();
	}

	@Override
	public int readInt() throws IOException {
		return buffer.getInt();
	}

	@Override
	public long readLong() throws IOException {
		return buffer.getLong();
	}

	@Override
	public float readFloat() throws IOException {
		return buffer.getLong();
	}

	@Override
	public double readDouble() throws IOException {
		return buffer.getDouble();
	}

	@Override
	public String readLine() throws IOException {
		throw new NotImplementedException();
	}

	@Override
	public String readUTF() throws IOException {
		throw new NotImplementedException();
	}

	public void seek(long pos) {
		buffer.position((int) pos);
	}
}
