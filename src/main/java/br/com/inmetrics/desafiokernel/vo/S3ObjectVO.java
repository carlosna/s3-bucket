package br.com.inmetrics.desafiokernel.vo;

public class S3ObjectVO {
	private String name;
    
	public S3ObjectVO(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
