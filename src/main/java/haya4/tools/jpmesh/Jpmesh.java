package haya4.tools.jpmesh;

import java.awt.geom.Rectangle2D;

import org.geotools.geometry.DirectPosition2D;

public class Jpmesh {

	/**
	 * 地域メッシュコードの位置（南西角）を取得する
	 * @param str	地域メッシュコード（１～４次コード）
	 * @return	地域メッシュコードの南西角位置。変換できなかった場合にはNULL。
	 */
	public static DirectPosition2D getPosition(String str) {
		if (str != null) {
        	try {
        		Long.parseLong(str);
    			if (str.length() >= 4) {
    				double lat1 = Double.parseDouble(str.substring(0, 2));
    				double lon1 = Double.parseDouble(str.substring(2, 4));
    				double lat = lat1 / 1.5 * 3600;
    				double lon = (lon1 + 100) * 3600;

        			if (str.length() >= 6) {
        				double lat2 = Double.parseDouble(str.substring(4, 5));
        				double lon2 = Double.parseDouble(str.substring(5, 6));
        				lat = lat + (lat2 * 300);
        				lon = lon + (lon2 * 450);
        			}
    				
        			if (str.length() >= 8) {
        				double lat3 = Double.parseDouble(str.substring(6, 7));
        				double lon3 = Double.parseDouble(str.substring(7, 8));
        				lat = lat + (lat3 * 30);
        				lon = lon + (lon3 * 45);
        			}
        			
        			if (str.length() >= 10) {
        				double lat4 = Double.parseDouble(str.substring(8, 9));
        				double lon4 = Double.parseDouble(str.substring(9, 10));
        				lat = lat + (lat4 * 3);
        				lon = lon + (lon4 * 4.5d);
        			}
        			
    				return new DirectPosition2D((lon / 3600), (lat / 3600));
    			}
        	}
        	catch (NumberFormatException e) {
        		return null;
        	}
		}
		return null;
	}

	/**
	 * 地域メッシュコードの位置（中央）を取得する
	 * @param str	地域メッシュコード（１～４次コード）
	 * @return	地域メッシュコードの中央位置。変換できなかった場合にはNULL。
	 */
	public static DirectPosition2D getCenterPosition(String str) {
		DirectPosition2D ret = getPosition(str);
		if (ret != null) {
			double lat = (ret.getY());
			double lon = (ret.getX());
			if (str.length() >= 10) {
				lat = (lat + (7.5d / 3600.0d));
				lon = (lon + (11.25d / 3600.0d));
			}
			else if (str.length() >= 8) {
				lat = (lat + (15.0d / 3600.0d));
				lon = (lon + (22.5d / 3600.0d));
			}
			else if (str.length() >= 6) {
				lat = (lat + (30.0d / 3600.0d));
				lon = (lon + (45.0d / 3600.0d));
			}
			else if (str.length() >= 4) {
				lat = (lat + (60.0d / 3600.0d));
				lon = (lon + (90.0d / 3600.0d));
			}
			return new DirectPosition2D(lon, lat);
		}
		return null;
	}
	
	/**
	 * 地域メッシュコードの矩形領域を取得する
	 * @param str	地域メッシュコード（３次コード）
	 * @return	地域メッシュコードの矩形領域。変換できなかった場合にはNULL。
	 */
	public static Rectangle2D getRectangle(String str) {
		DirectPosition2D ret = getPosition(str);
		if (ret != null) {
			double lat = ret.getY();
			double lon = ret.getX();
			double lat2 = 0;
			double lon2 = 0;
			if (str.length() >= 10) {
				lat2 = 15d / 3600.0d;
				lon2 = 22.5d / 3600.0d;
			}
			else if (str.length() >= 8) {
				lat2 = 30.0d / 3600.0d;
				lon2 = 45.0d / 3600.0d;
			}
			else if (str.length() >= 6) {
				lat2 = 60.0d / 3600.0d;
				lon2 = 90.0d / 3600.0d;
			}
			else if (str.length() >= 4) {
				lat2 = 120.0d / 3600.0d;
				lon2 = 180.0d / 3600.0d;
			}
			return new Rectangle2D.Double(lon, lat, lon2, lat2);
		}
		return null;
	}
}
