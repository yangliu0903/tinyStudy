package org.tinygroup.websample;

import static org.tinygroup.commons.tools.ObjectUtil.defaultIfNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.tinygroup.commons.io.ByteArray;
import org.tinygroup.commons.io.ByteArrayInputStream;
import org.tinygroup.commons.io.ByteArrayOutputStream;
import org.tinygroup.commons.io.StreamUtil;

public class WebServiceResponse extends HttpServletResponseWrapper {
	private ServletOutputStream servletOutputStream;

	private PrintWriter writer;

	public WebServiceResponse(HttpServletResponse response) {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (servletOutputStream == null) {
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			servletOutputStream = new BufferedServletOutputStream(bytes);
		}
		return servletOutputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (writer == null) {
			StringWriter chars = new StringWriter();
			writer = new BufferedServletWriter(chars);
		}
		return writer;
	}

	@Override
	public void flushBuffer() throws IOException {

		if (writer != null) {
			writer.flush();
		} else if (servletOutputStream != null) {
			servletOutputStream.flush();
		}
	}

	@Override
	public void resetBuffer() {
		if (writer != null) {
			StringWriter chars = new StringWriter();
			((BufferedServletWriter)writer).updateWriter(chars);
		} else if (servletOutputStream != null) {
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			((BufferedServletOutputStream) servletOutputStream).updateOutputStream(bytes);
		}
	}
	
	public ByteArrayOutputStream getByteArrayOutputStream(){
		return ((BufferedServletOutputStream) servletOutputStream).bytes;
	}
	
	public StringWriter getBufferedServletWriter(){
		return ((BufferedServletWriter)writer).stringWriter;
	}
	

	private static class BufferedServletOutputStream extends
			ServletOutputStream {
		private ByteArrayOutputStream bytes;

		public BufferedServletOutputStream(ByteArrayOutputStream bytes) {
			this.bytes = bytes;
		}

		public void updateOutputStream(ByteArrayOutputStream bytes) {
			this.bytes = bytes;
		}

		public void write(int b) throws IOException {
			bytes.write((byte) b);
		}

		public void write(byte[] b) throws IOException {
			write(b, 0, b.length);
		}

		public void write(byte[] b, int off, int len) throws IOException {
			bytes.write(b, off, len);
		}

		public void flush() throws IOException {
			bytes.flush();
		}

		public void close() throws IOException {
			bytes.flush();
			bytes.close();
		}
	}

	/** 代表一个将内容保存在内存中的<code>PrintWriter</code>。 */
	private static class BufferedServletWriter extends PrintWriter {
		private StringWriter stringWriter;
		public BufferedServletWriter(StringWriter chars) {
			super(chars);
			this.stringWriter=chars;
		}

		public void updateWriter(StringWriter chars) {
			this.out = chars;
			this.stringWriter=chars;
		}
	}

	/** 将<code>Writer</code>适配到<code>ServletOutputStream</code>。 */
	private static class WriterOutputStream extends ServletOutputStream {
		private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		private Writer writer;
		private String charset;

		public WriterOutputStream(Writer writer, String charset) {
			this.writer = writer;
			this.charset = defaultIfNull(charset, "ISO-8859-1");
		}

		public void write(int b) throws IOException {
			buffer.write((byte) b);
		}

		public void write(byte[] b) throws IOException {
			buffer.write(b, 0, b.length);
		}

		public void write(byte[] b, int off, int len) throws IOException {
			buffer.write(b, off, len);
		}

		public void flush() throws IOException {
			ByteArray bytes = buffer.toByteArray();

			if (bytes.getLength() > 0) {
				ByteArrayInputStream inputBytes = new ByteArrayInputStream(
						bytes.getRawBytes(), bytes.getOffset(),
						bytes.getLength());
				InputStreamReader reader = new InputStreamReader(inputBytes,
						charset);

				StreamUtil.io(reader, writer, true, false);
				writer.flush();

				buffer.reset();
			}
		}

		public void close() throws IOException {
			this.flush();
		}
	}

}