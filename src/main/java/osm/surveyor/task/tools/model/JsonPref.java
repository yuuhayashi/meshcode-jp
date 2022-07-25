package osm.surveyor.task.tools.model;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;
import osm.surveyor.task.util.JsonTemple;
import osm.surveyor.task.util.Point;

/**
 *	{"prefcode":1, "prefname":"北海道", "point":[143.35,43.479]},
 */
@Getter
@Setter
public class JsonPref extends JsonTemple {
	
	private int prefcode;
	
	private String prefname;
	
	private Point point;
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		boolean c = false;
		sb.append("{");
		c = out(c, sb, "prefcode", String.valueOf(this.prefcode));
		c = outStr(c, sb, "prefname", this.prefname);
		c = out(c, sb, "point", this.point);
		sb.append("}");
		return sb.toString();
	}
	
	public void parse(JsonNode node) {
		JsonNode node1 = node.get("prefcode");
		if (node1 != null) {
			this.prefcode = node1.intValue();
		}
		
		node1 = node.get("prefname");
		if (node1 != null) {
			this.prefname = node1.textValue();
		}

		node1 = node.get("point");
		if (node1 != null) {
			this.point = new Point();
			this.point.parse(node1);
		}
	}
}
