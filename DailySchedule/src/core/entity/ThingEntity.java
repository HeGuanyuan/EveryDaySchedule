package core.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Class ThingEntity 事件实体类
 * @Author He Guanyuan 2015-8-1 下午7:59:17
 */
public class ThingEntity {
	private String name;
	private String id;
	private String color;
	private List<String> remarks;
	private List<String> evaluations;
	
	/** 是否展开 */
	private boolean isExpanded;

	public ThingEntity() {
		name = "";
		id = "";
		setColor("");
		remarks = new ArrayList<String>();
		setEvaluations(new ArrayList<String>());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<String> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<String> evaluations) {
		this.evaluations = evaluations;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
