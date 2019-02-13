package br.com.inmetrics.desafiokernel.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class S3ObjectVO implements Serializable {
	private String name;
	private Long size;

	@JsonFormat(pattern = "dd/MM/YYYY HH:mm:ss")
	private Date lastModified;

	public S3ObjectVO(String name) {
		this.name = name;
	}

	public S3ObjectVO(String name, Long size, Date lastModified) {
		this.name = name;
		this.lastModified = lastModified;
		this.size = size;
	}

	public long getSize() { return size; }

	public void setSize(long size) { this.size = size; }

	public Date getLastModified() { return lastModified; }

	public void setLastModified(Date lastModified) { this.lastModified = lastModified; }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		S3ObjectVO that = (S3ObjectVO) o;
		return size == that.size &&
				name.equals(that.name) &&
				lastModified.equals(that.lastModified);
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((lastModified == null) ? 0 : lastModified.hashCode());
		return result;

	}
}
