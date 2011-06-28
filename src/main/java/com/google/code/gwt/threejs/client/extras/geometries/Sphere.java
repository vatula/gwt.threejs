package com.google.code.gwt.threejs.client.extras.geometries;

import java.util.ArrayList;
import java.util.List;

import com.google.code.gwt.threejs.client.core.Face3;
import com.google.code.gwt.threejs.client.core.Geometry;
import com.google.code.gwt.threejs.client.core.UV;
import com.google.code.gwt.threejs.client.core.Vector3;
import com.google.code.gwt.threejs.client.core.Vertex;
import com.google.common.collect.Lists;

public final class Sphere extends Geometry {
	public Sphere(int radius, int segmentsWidth, int segmentsHeight){
		super();
		double gridX = segmentsWidth,
			gridY = segmentsHeight,
			iHor = Math.max(3, gridX),
			iVer = Math.max(2, gridY);
		double pi = Math.PI;
		List<List<Integer>> aVtc = Lists.newArrayList();
		for (int j = 0; j < (iVer + 1); j++) {

			double fRad1 = j / iVer;
			double fZ = radius * Math.cos( fRad1 * pi );
			double fRds = radius * Math.sin( fRad1 * pi );
			List<Integer> aRow = Lists.newArrayList();
			int oVtx = 0;

			for (int i = 0; i < iHor; i++) {

				double fRad2 = 2 * i / iHor;
				double fX = fRds * Math.sin(fRad2 * pi);
				double fY = fRds * Math.cos(fRad2 * pi);

				if (!((j == 0 || j == iVer) && i > 0)) {
					Vertex v = new Vertex(new Vector3(fY, fZ, fX));
					this.vertices.add(v);
					oVtx = this.vertices.size() - 1;
				}
				aRow.add(oVtx);
			}
			aVtc.add(aRow);

		}

		Vector3 n1, n2, n3;
		int iVerNum = aVtc.size();

		for (int j = 0; j < iVerNum; j++){

			int iHorNum = aVtc.get(j).size();

			if (j > 0) {

				for (int i = 0; i < iHorNum; i++) {

					boolean bEnd = i == (iHorNum - 1);
					int aP1 = aVtc.get(j).get(bEnd ? 0 : i + 1);
					int aP2 = aVtc.get(j).get(bEnd ? iHorNum - 1 : i);
					int aP3 = aVtc.get(j - 1).get(bEnd ? iHorNum - 1 : i);
					int aP4 = aVtc.get(j - 1).get(bEnd ? 0 : i + 1);

					double fJ0 = j/((double)iVerNum - 1);
					double fJ1 = (j - 1)/((double)iVerNum - 1);
					double fI0 = (i + 1)/(double)iHorNum;
					double fI1 = i/(double)iHorNum;

					UV aP1uv = new UV(1 - fI0, fJ0);
					UV aP2uv = new UV(1 - fI1, fJ0);
					UV aP3uv = new UV(1 - fI1, fJ1);
					UV aP4uv = new UV(1 - fI0, fJ1);

					if (j < (aVtc.size() - 1)) {

						n1 = this.vertices.get(aP1).getPosition().clone();
						n2 = this.vertices.get(aP2).getPosition().clone();
						n3 = this.vertices.get(aP3).getPosition().clone();
						n1.normalize();
						n2.normalize();
						n3.normalize();

						ArrayList<Vector3> normals = Lists.newArrayList(new Vector3(n1.getX(), n1.getY(), n1.getZ()), new Vector3(n2.getX(), n2.getY(), n2.getZ()), new Vector3(n3.getX(), n3.getY(), n3.getZ()));
						this.faces.add(new Face3(aP1, aP2, aP3, normals, null, null));

						this.faceVertexUvs.get(0).add(Lists.newArrayList(aP1uv, aP2uv, aP3uv));
					}

					if (j > 1) {
						n1 = this.vertices.get(aP1).getPosition().clone();
						n2 = this.vertices.get(aP3).getPosition().clone();
						n3 = this.vertices.get(aP4).getPosition().clone();
						n1.normalize();
						n2.normalize();
						n3.normalize();

						ArrayList<Vector3> normals = Lists.newArrayList(
							new Vector3(n1.getX(), n1.getY(), n1.getZ()),
							new Vector3(n2.getX(), n2.getY(), n2.getZ()),
							new Vector3(n3.getX(), n3.getY(), n3.getZ())
						);
						this.faces.add(new Face3(aP1, aP3, aP4, normals, null, null));

						this.faceVertexUvs.get(0).add(Lists.newArrayList(aP1uv, aP3uv, aP4uv));
					}

				}
			}
		}

		this.computeCentroids();
		this.computeFaceNormals(false);
		this.computeVertexNormals();

		this.boundingSphere = new BoundingSphere(radius);
	}
}
