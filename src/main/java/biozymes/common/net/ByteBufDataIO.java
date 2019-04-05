package biozymes.common.net;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;

/**
 * Wraps a {@link ByteBuf} with both the {@link DataInput} and
 * {@link DataOutput} interfaces, so you can read/write NBT data.
 * 
 * @author mailmindlin
 */
public class ByteBufDataIO implements DataInput, DataOutput {
	protected final ByteBuf buf;
	
	public ByteBufDataIO(ByteBuf buf) {
		this.buf = buf;
	}
	
	@Override
	public void readFully(byte[] b) throws IOException {
		buf.readBytes(b);
	}
	
	@Override
	public void readFully(byte[] b, int off, int len) throws IOException {
		buf.readBytes(b, off, len);
	}
	
	@Override
	public int skipBytes(int n) throws IOException {
		buf.skipBytes(n);
		return n;
	}
	
	@Override
	public boolean readBoolean() throws IOException {
		return buf.readBoolean();
	}
	
	@Override
	public byte readByte() throws IOException {
		return buf.readByte();
	}
	
	@Override
	public int readUnsignedByte() throws IOException {
		return buf.readUnsignedByte();
	}
	
	@Override
	public short readShort() throws IOException {
		return buf.readShort();
	}
	
	@Override
	public int readUnsignedShort() throws IOException {
		return buf.readUnsignedShort();
	}
	
	@Override
	public char readChar() throws IOException {
		return buf.readChar();
	}
	
	@Override
	public int readInt() throws IOException {
		return buf.readInt();
	}
	
	@Override
	public long readLong() throws IOException {
		return buf.readLong();
	}
	
	@Override
	public float readFloat() throws IOException {
		return buf.readFloat();
	}
	
	@Override
	public double readDouble() throws IOException {
		return buf.readDouble();
	}
	
	@Override
	public String readLine() throws IOException {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public String readUTF() throws IOException {
		return ByteBufUtils.readUTF8String(buf);
	}
	
	/**
	 * Read a NBT tag from the stream
	 * @param accounter 
	 * 	Size tracker
	 * @return NBT tag
	 * @throws IOException
	 * @see CompressedStreamTools#read(DataInput, NBTSizeTracker)
	 * @see #readNBT()
	 * @see #writeNBT(NBTTagCompound)
	 */
	public NBTTagCompound readNBT(NBTSizeTracker accounter) throws IOException {
		return CompressedStreamTools.read(this, accounter);
	}
	
	/**
	 * Read a NBT tag from the stream. Same as {@link #readNBT(NBTSizeTracker)}
	 * called with {@link NBTSizeTracker#INFINITE}.
	 * 
	 * @return NBT tag
	 * @throws IOException
	 * @see CompressedStreamTools#read(DataInput, NBTSizeTracker)
	 * @see #readNBT(NBTSizeTracker)
	 */
	public NBTTagCompound readNBT() throws IOException {
		return readNBT(NBTSizeTracker.INFINITE);
	}
	
	@Override
	public void write(int b) throws IOException {
		buf.writeByte(b);
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		buf.writeBytes(b);
	}
	
	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		buf.writeBytes(b, off, len);
	}
	
	@Override
	public void writeBoolean(boolean v) throws IOException {
		buf.writeBoolean(v);
	}
	
	@Override
	public void writeByte(int v) throws IOException {
		buf.writeByte(v);
	}
	
	@Override
	public void writeShort(int v) throws IOException {
		buf.writeShort(v);
	}
	
	@Override
	public void writeChar(int v) throws IOException {
		buf.writeChar(v);
	}
	
	@Override
	public void writeInt(int v) throws IOException {
		buf.writeInt(v);
	}
	
	@Override
	public void writeLong(long v) throws IOException {
		buf.writeLong(v);
	}
	
	@Override
	public void writeFloat(float v) throws IOException {
		buf.writeFloat(v);
	}
	
	@Override
	public void writeDouble(double v) throws IOException {
		buf.writeDouble(v);
	}
	
	@Override
	public void writeBytes(String s) throws IOException {
		buf.writeBytes(s.getBytes());
	}
	
	@Override
	public void writeChars(String s) throws IOException {
		for (int i = 0; i < s.length(); i++)
			buf.writeChar(s.charAt(i));
	}
	
	@Override
	public void writeUTF(String s) throws IOException {
		ByteBufUtils.writeUTF8String(buf, s);
	}
	
	/**
	 * Writes NBT tag to buffer.
	 * @param nbt data to write
	 * @throws IOException
	 * @see CompressedStreamTools#write(NBTTagCompound, DataOutput)
	 * @see #readNBT(NBTSizeTracker)
	 */
	public void writeNBT(NBTTagCompound nbt) throws IOException {
		CompressedStreamTools.write(nbt, this);
	}
	
}
