package br.com.healthhistoryonline.sysmodel;

public class File {
	private int fileCode;
	private String fileName;
	
	public File(String fileName) {
		this.fileName = fileName;
	}

	public int getFileCode() {
		return fileCode;
	}

	public void setFileCode(int fileCode) {
		this.fileCode = fileCode;
	}

	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}