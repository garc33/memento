package fr.herman.memento;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.profile.StackProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import fr.herman.memento.index.MemoryMappedIndex;
import fr.herman.memento.index.SimpleIndexStructure;
import fr.herman.memento.schema.Schema;
import fr.herman.memento.sequence.Sequence;
import fr.herman.memento.storage.MemoryMappedFileDataStore;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class PerfTest {
	Database<User> database;

	User user;

	public static class User {
		String name;
		int age;
	}

	@Setup
	public void setup() throws Exception {
		database = new Database<User>(new MemoryMappedFileDataStore(new RandomAccessFile("test.dat", "rw")),
				new MemoryMappedIndex(new RandomAccessFile("test.idx", "rw"), new SimpleIndexStructure()),
				new Sequence() {
					private long seq = 0;

					@Override
					public long nextValue() {
						return seq++;
					}
				}, new Schema<User>() {
					private byte[] buffer = new byte[8000];

					@Override
					public User read(DataInput input) {
						return null;
					}

					@Override
					public long write(User data, DataOutput output) throws IOException {
						buffer[0] = (byte) (data.age >> 24);
						buffer[1] = (byte) (data.age >> 16);
						buffer[2] = (byte) (data.age >> 8);
						buffer[3] = (byte) (data.age /* >> 0 */);
						data.name.getBytes(0, data.name.length(), buffer, 4);
						output.write(buffer, 0, 4 + data.name.length() * 2);
						return 4 + data.name.length() * 2;
					}
				});
		user = new User();
		user.name = "marc herman";
		user.age = 31;
	}

	@Benchmark
	@Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
	@Measurement(iterations = 1, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
	public void benchInsert() throws Exception {
		database.insert(user);
	}

	// @Benchmark
	// @Warmup(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
	// @Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.MILLISECONDS)
	// public void bench() throws Exception {
	// Math.abs(-2);
	// }

	public static void main(String... strings) throws Exception {
		Options opt = new OptionsBuilder().include(PerfTest.class.getSimpleName()).addProfiler(StackProfiler.class)
				// .addProfiler(GCProfiler.class)
				.build();

		new Runner(opt).run();
	}
}
