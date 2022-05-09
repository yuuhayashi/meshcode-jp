package haya4.tools.jpmesh;

import java.awt.geom.Rectangle2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.geotools.geometry.DirectPosition2D;

public class Jpmesh {

	public static String getMesh(double lon, double lat, int level) {
		double lon1 = lon - 100.0d;
		double lat1 = lat * 3.0d / 2.0d;
		if (level >= 1) {
			String x1 = get2(lon1);
			String y1 = get2(lat1);
			
			if (level >= 2) {
				double lon2 = lon1 - Double.parseDouble(x1);
				lon2 = lon2 * 10;
				String x2 = get1(lon2);
				
				double lat2 = lat1 - Double.parseDouble(y1);
				lat2 = lat2 * 10;
				String y2 = get1(lat2);
				
				if (level >= 3) {
					double lon3 = lon2 - Double.parseDouble(x2);
					lon3 = lon3 * 10;
					String x3 = get1(lon3);
					
					double lat3 = lat2 - Double.parseDouble(y2);
					lat3 = lat3 * 10;
					String y3 = get1(lat3);
					
					if (level >= 4) {
						double lon4 = lon3 - Double.parseDouble(x3);
						lon4 = lon4 * 10;
						String x4 = get1(lon4);
						
						double lat4 = lat3 - Double.parseDouble(y3);
						lat4 = lat4 * 10;
						String y4 = get1(lat4);
						
						// 4次メッシュ
						return (y1 + x1 + y2 + x2 + y3 + x3 + y4 + x4);
					}
					else {
						// 3次メッシュ
						return (y1 + x1 + y2 + x2 + y3 + x3);
					}
				}
				else {
					// 2次メッシュ
					return (y1 + x1 + y2 + x2);
				}
			}
			else {
				// １次メッシュ
				return (y1 + x1);
			}
		}
		return null;
	}
	
	private static String get2(double d) {
		DecimalFormat s2 = new DecimalFormat("##");
		s2.setRoundingMode(RoundingMode.DOWN);
		return s2.format(d);
	}

	private static String get1(double d) {
		DecimalFormat s1 = new DecimalFormat("#");
		s1.setRoundingMode(RoundingMode.DOWN);
		return s1.format(d);
	}
	
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
