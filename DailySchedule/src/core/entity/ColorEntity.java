package core.entity;

public class ColorEntity {

	private String name;
	private String colorId;
	private String code;
	private boolean isUsed;

	public ColorEntity() {
	}

	public ColorEntity (String name, String id, String code){
		this.name = name;
		this.colorId = id;
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
}
