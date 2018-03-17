package model;

public class ModelImpl implements Model {

	private String content, name, extension, language;
	
	@Override
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setLanguage(String lng) {
		language = lng;
	}

	@Override
	public String getLanguage() {
		return language;
	}

	@Override
	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String getExtension() {
		return extension;
	}

}
