package core.entity;

import java.util.List;


/**
 * @Class ThingEntity �¼�ʵ����
 * @Author He Guanyuan 2015-8-1 ����7:59:17
 */
public class ThingEntity {
	private String name;
	private String color;
	private String id;
	private List<String> remarks;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getRemarks() {
		return remarks;
	}

	public void setRemarks(List<String> remarks) {
		this.remarks = remarks;
	}

}
