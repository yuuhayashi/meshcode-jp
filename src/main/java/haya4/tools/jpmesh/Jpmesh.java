package haya4.tools.jpmesh;

import java.awt.geom.Rectangle2D;

import org.geotools.geometry.DirectPosition2D;

public class Jpmesh {

	/**
	 * 地域メッシュコードの位置（南西角）を取得する
	 * @param str	地域メッシュコード
	 * @return	地域メッシュコードの南西角位置
	 */
	public static DirectPosition2D getPosition(String str) {
		if (str != null) {
			if (str.length() == 8) {
				double lat1 = Double.parseDouble(str.substring(0, 2));
				double lat2 = Double.parseDouble(str.substring(4, 5));
				double lat3 = Double.parseDouble(str.substring(6, 7));
				
				double lon1 = Double.parseDouble(str.substring(2, 4));
				double lon2 = Double.parseDouble(str.substring(5, 6));
				double lon3 = Double.parseDouble(str.substring(7, 8));

				double lat = (((lat1 / 1.5 * 3600) + (lat2 * 5 * 60) + (lat3 * 60)) / 3600);
				double lon = ((((lon1 + 100) * 3600) + (lon2 * 7.5 * 60) + (lon3 * 45)) / 3600);
				return new DirectPosition2D(lon, lat);
			}
		}
		return null;
	}

	/**
	 * 地域メッシュコードの位置（中央）を取得する
	 * @param str	地域メッシュコード
	 * @return	地域メッシュコードの中央位置
	 */
	public static DirectPosition2D getCenterPosition(String str) {
		DirectPosition2D ret = getPosition(str);
		if (ret != null) {
			double lat = (ret.getY() + (30d / 2 / 3600d));
			double lon = (ret.getX() + (45d / 2 / 3600d));
			return new DirectPosition2D(lon, lat);
		}
		return null;
	}
	
	public static Rectangle2D getRectangle(String str) {
		DirectPosition2D ret = getPosition(str);
		if (ret != null) {
			double lat = (ret.getY());
			double lon = (ret.getX());
			return new Rectangle2D.Double(lon, lat, (45d / 3600d), (30d / 3600d));
		}
		return null;
	}
}
