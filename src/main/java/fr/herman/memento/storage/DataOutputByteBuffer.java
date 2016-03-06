package fr.herman.memento.storage;

import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class DataOutputByteBuffer implements DataOutput {
	public final ByteBuffer buffer;

	public DataOutputByteBuffer(ByteBuffer buffer) {
		this.buffer = buffer;
	}

	@Override
	public void write(int b) throws IOException {
		buffer.putInt(b);
	}

	@Override
	public void write(byte[] b) throws IOException {
		buffer.put(b);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		buffer.put(b, off, len);
	}

	@Override
	public void writeBoolean(boolean v) throws IOException {
		throw new NotImplementedException();
	}

	@Override
	public void writeByte(int v) throws IOException {
		buffer.put((byte) v);
	}

	@Override
	public void writeShort(int v) throws IOException {
		buffer.putShort((short) v);
	}

	@Override
	public void writeChar(int v) throws IOException {
		buffer.putChar((char) v);
	}

	@Override
	public void writeInt(int v) throws IOException {
		buffer.putInt(v);
	}

	@Override
	public void writeLong(long v) throws IOException {
		buffer.putLong(v);
	}

	@Override
	public void writeFloat(float v) throws IOException {
		buffer.putFloat(v);
	}

	@Override
	public void writeDouble(double v) throws IOException {
		buffer.putDouble(v);
	}

	@Override
	public void writeBytes(String s) throws IOException {
		for (int i = 0; i < s.length(); i++)
			buffer.putChar(s.charAt(i));
	}

	@Override
	public void writeChars(String s) throws IOException {
		for (int i = 0; i < s.length(); i++)
			buffer.putChar(s.charAt(i));
	}

	@Override
	public void writeUTF(String s) throws IOException {
		throw new NotImplementedException();
	}

	public void seek(long pos) {
		buffer.position((int) pos);
	}
}
