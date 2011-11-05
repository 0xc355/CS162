package edu.berkeley.cs.cs162.Writable;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * A writable list. This thing uses a bit of messy reflection to preserve generic arguments...
 * 
 * @author xshi
 *
 * @param <E>
 */
public class WritableList<E extends Writable> extends ArrayList<E> implements Writable {
	private static final long serialVersionUID = -125579462824808520L;
	
	Class<E> storedClass;
	protected WritableList(Class<E> storedClass)
	{
		this.storedClass = this.getClass().getTypeParameters();
	}

	@Override
	public void readFrom(InputStream in) throws IOException {
		int length = DataTypeIO.readInt(in);
		this.clear();
		for (int i = 0; i < length; i++)
		{
			E e;
			try {
				e = storedClass.newInstance();
				e.readFrom(in);
				add(e);
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void writeTo(OutputStream out) throws IOException {
		DataTypeIO.writeInt(out, size());
		for (E e : this)
		{
			e.writeTo(out);
		}
	}
}
