package org.tinygroup.websample;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.tinygroup.commons.io.ByteArray;
import org.tinygroup.commons.io.ByteArrayInputStream;
import org.tinygroup.commons.io.StreamUtil;

public class WebServiceRequest extends HttpServletRequestWrapper {

	private boolean acquired;
	private BufferedServletInputStream servletInputStream;
	
	public WebServiceRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if(!acquired){
			ByteArray byteArray=StreamUtil.readBytes(super.getInputStream(),true);
			ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray.toByteArray());
			servletInputStream=new BufferedServletInputStream(byteArrayInputStream);
			acquired=true;
		}
		servletInputStream.reset();
		return servletInputStream;
	}
	
	
	
	
	private static class BufferedServletInputStream extends ServletInputStream{
		private ByteArrayInputStream byteArrayInputStream;
		
		public BufferedServletInputStream(
				ByteArrayInputStream byteArrayInputStream) {
			super();
			this.byteArrayInputStream = byteArrayInputStream;
		}
		@Override
		public synchronized void reset() throws IOException {
			byteArrayInputStream.reset();
		}
		@Override
		public int read() throws IOException {
			return byteArrayInputStream.read();
		}
		@Override
		public int read(byte[] b) throws IOException {
			return byteArrayInputStream.read(b);
		}
		@Override
		public int read(byte[] b, int off, int len) throws IOException {
			return byteArrayInputStream.read(b, off, len);
		}
		@Override
		public long skip(long n) throws IOException {
			return byteArrayInputStream.skip(n);
		}
		@Override
		public int available() throws IOException {
			return byteArrayInputStream.available();
		}
		@Override
		public void close() throws IOException {
			byteArrayInputStream.close();
		}
		@Override
		public synchronized void mark(int readlimit) {
			byteArrayInputStream.mark(readlimit);
		}
		@Override
		public boolean markSupported() {
			return byteArrayInputStream.markSupported();
		}
		
		
		
	}

}
