package org.catspaw.cherubim.socketpool;

import java.io.Serializable;

/**
 * socket的参数，对应java.net.Socket的setXxx方法
 */
public class SocketConfig implements Serializable {

	private static final long	serialVersionUID	= 2957542008365334296L;
	private boolean				keepAlive			= false;
	private boolean				oobInline			= false;
	private int					receiveBufferSize	= 8192;				//default is 8192
	private boolean				reuseAddress		= false;
	private int					sendBufferSize		= 8192;				//default is 8192
	private int					soTimeout			= 0;					//default is 0
	private boolean				tcpNoDelay			= false;

	public boolean isKeepAlive() {
		return keepAlive;
	}

	public boolean isOobInline() {
		return oobInline;
	}

	public int getReceiveBufferSize() {
		return receiveBufferSize;
	}

	public boolean isReuseAddress() {
		return reuseAddress;
	}

	public int getSendBufferSize() {
		return sendBufferSize;
	}

	public int getSoTimeout() {
		return soTimeout;
	}

	public boolean isTcpNoDelay() {
		return tcpNoDelay;
	}

	public void setKeepAlive(boolean keepAlive) {
		this.keepAlive = keepAlive;
	}

	public void setOobInline(boolean oobInline) {
		this.oobInline = oobInline;
	}

	public void setReceiveBufferSize(int receiveBufferSize) {
		this.receiveBufferSize = receiveBufferSize;
	}

	public void setReuseAddress(boolean reuseAddress) {
		this.reuseAddress = reuseAddress;
	}

	public void setSendBufferSize(int sendBufferSize) {
		this.sendBufferSize = sendBufferSize;
	}

	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

	public void setTcpNoDelay(boolean tcpNoDelay) {
		this.tcpNoDelay = tcpNoDelay;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (keepAlive ? 1231 : 1237);
		result = prime * result + (oobInline ? 1231 : 1237);
		result = prime * result + receiveBufferSize;
		result = prime * result + (reuseAddress ? 1231 : 1237);
		result = prime * result + sendBufferSize;
		result = prime * result + soTimeout;
		result = prime * result + (tcpNoDelay ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		SocketConfig other = (SocketConfig) obj;
		if (keepAlive != other.keepAlive) {
			return false;
		}
		if (oobInline != other.oobInline) {
			return false;
		}
		if (receiveBufferSize != other.receiveBufferSize) {
			return false;
		}
		if (reuseAddress != other.reuseAddress) {
			return false;
		}
		if (sendBufferSize != other.sendBufferSize) {
			return false;
		}
		if (soTimeout != other.soTimeout) {
			return false;
		}
		if (tcpNoDelay != other.tcpNoDelay) {
			return false;
		}
		return true;
	}
}
