package core.entity;

import java.util.List;


/**
 * @Class ThingEntity 事件实体类
 * @Author He Guanyuan 2015-8-1 下午7:59:17
 */
public class ThingEntity {
	private String name;
	private String color;
	private String id;
	private List<String> remarks;
	/**是否展开flag*/
	private boolean isExpanded;

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

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

}
