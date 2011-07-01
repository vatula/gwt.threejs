package com.google.code.gwt.threejs.client.extras.geometries;

import com.google.code.gwt.threejs.client.core.Face4;
import com.google.code.gwt.threejs.client.core.Geometry;
import com.google.code.gwt.threejs.client.core.UV;
import com.google.code.gwt.threejs.client.core.Vector3;
import com.google.code.gwt.threejs.client.core.Vertex;
import com.google.common.collect.Lists;

public final class Plane extends Geometry {
	public Plane(int width, int height){
		this(width, height, 1, 1);
	}
	public Plane(int width, int height, int segmentsWidth, int segmentsHeight){
		super();
		double gridX = segmentsWidth,
			gridY = segmentsHeight,
			gridX1 = gridX+1,
			gridY1 = gridY+1;
		
		double widthHalf = width/2,
			heightHalf = height/2,
			tSegmentWidth = width/gridX,
			tSegmentHeight = height/gridY;
		
		for(int iy = 0; iy < gridY1; iy++){
			for(int ix = 0; ix < gridX1; ix++) {
				double x = ix * tSegmentWidth - widthHalf;
				double y = iy * tSegmentHeight - heightHalf;
				this.getVertices().add(new Vertex(new Vector3(x,-y,0)));
			}
		}

		for(int iy = 0; iy < gridY; iy++) {
			for(int ix = 0; ix < gridX; ix++) {
				int a = ix + (int)gridX1 * iy;
				int b = ix + (int)gridX1 * (iy + 1);
				int c = (ix + 1) + (int)gridX1 * (iy + 1);
				int d = (ix + 1) + (int)gridX1 * iy;

				this.faces.add(new Face4(a, b, c, d));
				this.faceVertexUvs.get(0).add(Lists.newArrayList(
					new UV(ix/gridX, iy/gridY),
					new UV(ix/gridX, (iy + 1) / gridY),
					new UV((ix + 1 ) / gridX, ( iy + 1 ) / gridY),
					new UV((ix + 1 ) / gridX, iy / gridY)
				));

			}

		}
		
		this.computeCentroids();
		this.computeFaceNormals(false);
	}
}
