package chap3;

import java.util.List;

public class Project {
	private List<String> srcdirs;
	private String bindir;
	private BuildRunner build;
	private String classpath;
	
	public void build() {
		build.build(srcdirs,bindir);
	}
	// getter, setter
	public List<String> getSrcdirs() {
		return srcdirs;
	}
	public void setSrcdirs(List<String> srcdirs) {
		this.srcdirs = srcdirs;
	}
	public String getBindir() {
		return bindir;
	}
	public void setBindir(String bindir) {
		this.bindir = bindir;
	}
	public BuildRunner getBuild() {
		return build;
	}
	public void setBuild(BuildRunner build) {
		this.build = build;
	}
	public String getClasspath() {
		return classpath;
	}
	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}
}
