package com.google.code.gwt.threejs.client.core;

public class Matrix4 {
	
	protected static Vector3 __v1 = new Vector3();
	protected static Vector3 __v2 = new Vector3();
	protected static Vector3 __v3 = new Vector3();
	
	public Matrix4(){
		this(
			1,0,0,0,
			0,1,0,0,
			0,0,1,0,
			0,0,0,1
		); //n11=n22=n33=n44=1
	}
	public Matrix4(
		double n11, double n12, double n13, double n14,
		double n21, double n22, double n23, double n24,
		double n31, double n32, double n33, double n34,
		double n41, double n42, double n43, double n44
	){
		this.set(n11, n12, n13, n14, n21, n22, n23, n24, n31, n32, n33, n34, n41, n42, n43, n44);		
		this.flat = new double[16];
		this.m33 = new Matrix3();
	}
	
	public Matrix4 set(
		double n11, double n12, double n13, double n14,
		double n21, double n22, double n23, double n24,
		double n31, double n32, double n33, double n34,
		double n41, double n42, double n43, double n44
	){
		this.n11 = n11;
		this.n12 = n12;
		this.n13 = n13;
		this.n14 = n14;
		
		this.n21 = n21;
		this.n22 = n22;
		this.n23 = n23;
		this.n24 = n24;
		
		this.n31 = n31;
		this.n32 = n32;
		this.n33 = n33;
		this.n34 = n34;

		this.n41 = n41;
		this.n42 = n42;
		this.n43 = n43;
		this.n44 = n44;
		
		return this;
	}
	
	public void setN44(double n44) {
		this.n44 = n44;
	}

	public double getN44() {
		return n44;
	}

	public void setN43(double n43) {
		this.n43 = n43;
	}

	public double getN43() {
		return n43;
	}

	public void setN42(double n42) {
		this.n42 = n42;
	}

	public double getN42() {
		return n42;
	}

	public void setN41(double n41) {
		this.n41 = n41;
	}

	public double getN41() {
		return n41;
	}

	public void setN34(double n34) {
		this.n34 = n34;
	}

	public double getN34() {
		return n34;
	}

	public void setN33(double n33) {
		this.n33 = n33;
	}

	public double getN33() {
		return n33;
	}

	public void setN32(double n32) {
		this.n32 = n32;
	}

	public double getN32() {
		return n32;
	}

	public void setN31(double n31) {
		this.n31 = n31;
	}

	public double getN31() {
		return n31;
	}

	public void setN24(double n24) {
		this.n24 = n24;
	}

	public double getN24() {
		return n24;
	}

	public void setN23(double n23) {
		this.n23 = n23;
	}

	public double getN23() {
		return n23;
	}

	public void setN22(double n22) {
		this.n22 = n22;
	}

	public double getN22() {
		return n22;
	}

	public void setN21(double n21) {
		this.n21 = n21;
	}

	public double getN21() {
		return n21;
	}

	public void setN14(double n14) {
		this.n14 = n14;
	}

	public double getN14() {
		return n14;
	}

	public void setN13(double n13) {
		this.n13 = n13;
	}

	public double getN13() {
		return n13;
	}

	public void setN12(double n12) {
		this.n12 = n12;
	}

	public double getN12() {
		return n12;
	}

	public void setN11(double n11) {
		this.n11 = n11;
	}

	public double getN11() {
		return n11;
	}

	protected double n11;
	protected double n12;
	protected double n13;
	protected double n14;
	protected double n21;
	protected double n22;
	protected double n23;
	protected double n24;
	protected double n31;
	protected double n32;
	protected double n33;
	protected double n34;
	protected double n41;
	protected double n42;
	protected double n43;
	protected double n44;
	protected double[] flat;
	protected Matrix3 m33;
	
	// Methods
	
	public Matrix4 identity(){
		this.set(
			1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1
		);
		return this;
	};
	
	public Matrix4 copy(Matrix4 m){
		this.set(
			m.n11, m.n12, m.n13, m.n14,
			m.n21, m.n22, m.n23, m.n24,
			m.n31, m.n32, m.n33, m.n34,
			m.n41, m.n42, m.n43, m.n44
		);

		return this;
	};
	public Matrix4 lookAt(Vector3 eye, Vector3 center, Vector3 up){
		Vector3 x = Matrix4.__v1, y = Matrix4.__v2, z = Matrix4.__v3;

		z.sub(eye, center).normalize();

		if (z.length() == 0){
			z.z = 1;
		}

		x.cross(up, z).normalize();

		if (x.length() == 0) {
			z.x += 0.0001;
			x.cross(up, z).normalize();
		}

		y.cross(z, x).normalize();

		this.n11 = x.x; this.n12 = y.x; this.n13 = z.x;
		this.n21 = x.y; this.n22 = y.y; this.n23 = z.y;
		this.n31 = x.z; this.n32 = y.z; this.n33 = z.z;

		return this;
	};
	public Vector3 multiplyVector3(Vector3 v){
		double vx = v.x, vy = v.y, vz = v.z,
		d = 1 / (this.n41 * vx + this.n42 * vy + this.n43 * vz + this.n44);

		v.x = (this.n11 * vx + this.n12 * vy + this.n13 * vz + this.n14)*d;
		v.y = (this.n21 * vx + this.n22 * vy + this.n23 * vz + this.n24)*d;
		v.z = (this.n31 * vx + this.n32 * vy + this.n33 * vz + this.n34)*d;
		
		/*
		GWT.log("Matrix v.x: "+v.x+", v.y: "+v.y+", v.z: "+v.z);
		
		GWT.log("Matrix n11: "+n11+", n12: "+n12+", n13: "+n13+", n14: "+n14);
		GWT.log("Matrix n21: "+n21+", n22: "+n22+", n23: "+n23+", n24: "+n24);
		GWT.log("Matrix n31: "+n31+", n32: "+n32+", n33: "+n33+", n34: "+n34);
		GWT.log("Matrix n41: "+n41+", n42: "+n42+", n43: "+n43+", n44: "+n44);
		GWT.log("Matrix d: "+d);
		*/
		return v;
	};
	public Vector4 multiplyVector4(Vector4 v){
		double vx = v.x, vy = v.y, vz = v.z, vw = v.w;

		v.x = this.n11 * vx + this.n12 * vy + this.n13 * vz + this.n14 * vw;
		v.y = this.n21 * vx + this.n22 * vy + this.n23 * vz + this.n24 * vw;
		v.z = this.n31 * vx + this.n32 * vy + this.n33 * vz + this.n34 * vw;
		v.w = this.n41 * vx + this.n42 * vy + this.n43 * vz + this.n44 * vw;

		this.flatten();
		return v;
	};
	public Vector3 rotateAxis(Vector3 v){
		double vx = v.x, vy = v.y, vz = v.z;

		v.x = vx * this.n11 + vy * this.n12 + vz * this.n13;
		v.y = vx * this.n21 + vy * this.n22 + vz * this.n23;
		v.z = vx * this.n31 + vy * this.n32 + vz * this.n33;

		v.normalize();

		return v;
	};
	public Vector4 crossVector(Vector3 a){
		Vector4 v = new Vector4();
		Boolean isV4 = a.getClass() == Vector4.class;
		double w = isV4 ? ((Vector4)a).w : 0;

		v.x = this.n11 * a.x + this.n12 * a.y + this.n13 * a.z + this.n14 * w;
		v.y = this.n21 * a.x + this.n22 * a.y + this.n23 * a.z + this.n24 * w;
		v.z = this.n31 * a.x + this.n32 * a.y + this.n33 * a.z + this.n34 * w;

		v.w = isV4 ? this.n41 * a.x + this.n42 * a.y + this.n43 * a.z + this.n44 * w : 1;

		return v;
	};
	
	@SuppressWarnings("unused")
	public Matrix4 multiply(Matrix4 a, Matrix4 b){
		double a11 = a.n11, a12 = a.n12, a13 = a.n13, a14 = a.n14,
		a21 = a.n21, a22 = a.n22, a23 = a.n23, a24 = a.n24,
		a31 = a.n31, a32 = a.n32, a33 = a.n33, a34 = a.n34,
		a41 = a.n41, a42 = a.n42, a43 = a.n43, a44 = a.n44;

		double b11 = b.n11, b12 = b.n12, b13 = b.n13, b14 = b.n14,
		b21 = b.n21, b22 = b.n22, b23 = b.n23, b24 = b.n24,
		b31 = b.n31, b32 = b.n32, b33 = b.n33, b34 = b.n34,
		b41 = b.n41, b42 = b.n42, b43 = b.n43, b44 = b.n44;

		this.n11 = a11 * b11 + a12 * b21 + a13 * b31;
		this.n12 = a11 * b12 + a12 * b22 + a13 * b32;
		this.n13 = a11 * b13 + a12 * b23 + a13 * b33;
		this.n14 = a11 * b14 + a12 * b24 + a13 * b34 + a14;

		this.n21 = a21 * b11 + a22 * b21 + a23 * b31;
		this.n22 = a21 * b12 + a22 * b22 + a23 * b32;
		this.n23 = a21 * b13 + a22 * b23 + a23 * b33;
		this.n24 = a21 * b14 + a22 * b24 + a23 * b34 + a24;

		this.n31 = a31 * b11 + a32 * b21 + a33 * b31;
		this.n32 = a31 * b12 + a32 * b22 + a33 * b32;
		this.n33 = a31 * b13 + a32 * b23 + a33 * b33;
		this.n34 = a31 * b14 + a32 * b24 + a33 * b34 + a34;

		this.n41 = a41 * b11 + a42 * b21 + a43 * b31;
		this.n42 = a41 * b12 + a42 * b22 + a43 * b32;
		this.n43 = a41 * b13 + a42 * b23 + a43 * b33;
		this.n44 = a41 * b14 + a42 * b24 + a43 * b34 + a44;
		
		return this;
	};
	public Matrix4 multiplyToArray(Matrix4 a, Matrix4 b, double[] r){
		this.flattenToArrayOffset(r, this.multiply(a, b), 0);
		return this;
	};
	public Matrix4 multiplySelf(Matrix4 m){
		return this.multiply(this, m);
	};
	public Matrix4 multiplyScalar(double s){
		this.n11 *= s; this.n12 *= s; this.n13 *= s; this.n14 *= s;
		this.n21 *= s; this.n22 *= s; this.n23 *= s; this.n24 *= s;
		this.n31 *= s; this.n32 *= s; this.n33 *= s; this.n34 *= s;
		this.n41 *= s; this.n42 *= s; this.n43 *= s; this.n44 *= s;

		return this;
	};
	public double determinant(){
		double n11 = this.n11, n12 = this.n12, n13 = this.n13, n14 = this.n14,
		n21 = this.n21, n22 = this.n22, n23 = this.n23, n24 = this.n24,
		n31 = this.n31, n32 = this.n32, n33 = this.n33, n34 = this.n34,
		n41 = this.n41, n42 = this.n42, n43 = this.n43, n44 = this.n44;

		//TODO: make this more efficient
		//( based on http://www.euclideanspace.com/maths/algebra/matrix/functions/inverse/fourD/index.htm )
		return (
			n14 * n23 * n32 * n41-
			n13 * n24 * n32 * n41-
			n14 * n22 * n33 * n41+
			n12 * n24 * n33 * n41+

			n13 * n22 * n34 * n41-
			n12 * n23 * n34 * n41-
			n14 * n23 * n31 * n42+
			n13 * n24 * n31 * n42+

			n14 * n21 * n33 * n42-
			n11 * n24 * n33 * n42-
			n13 * n21 * n34 * n42+
			n11 * n23 * n34 * n42+

			n14 * n22 * n31 * n43-
			n12 * n24 * n31 * n43-
			n14 * n21 * n32 * n43+
			n11 * n24 * n32 * n43+

			n12 * n21 * n34 * n43-
			n11 * n22 * n34 * n43-
			n13 * n22 * n31 * n44+
			n12 * n23 * n31 * n44+

			n13 * n21 * n32 * n44-
			n11 * n23 * n32 * n44-
			n12 * n21 * n33 * n44+
			n11 * n22 * n33 * n44
		);
	};
	public Matrix4 transpose(){
		double tmp;

		tmp = this.n21; this.n21 = this.n12; this.n12 = tmp;
		tmp = this.n31; this.n31 = this.n13; this.n13 = tmp;
		tmp = this.n32; this.n32 = this.n23; this.n23 = tmp;

		tmp = this.n41; this.n41 = this.n14; this.n14 = tmp;
		tmp = this.n42; this.n42 = this.n24; this.n24 = tmp;
		tmp = this.n43; this.n43 = this.n34; this.n43 = tmp;

		return this;
	};
	
	public Matrix4 clone(){
		Matrix4 m = new Matrix4(
			this.n11,this.n12, this.n13, this.n14,
			this.n21, this.n22, this.n23, this.n24,
			this.n31, this.n32, this.n33, this.n34,
			this.n41, this.n42, this.n43, this.n44
		);

		return m;
	};
	
	public double[] flatten(){
		return this.flattenToArrayOffset(this.flat, 0);
	};
	public double[] flattenToArray(double[] flat){
		return this.flattenToArrayOffset(flat, 0);
	};
	public double[] flattenToArrayOffset(double[] flat, int offset){
		return this.flattenToArrayOffset(flat, this, offset);
	};
	private double[] flattenToArrayOffset(double[] flat, Matrix4 m, int offset){
		flat[offset] = m.n11; flat[offset+1] = m.n21; flat[offset+2] = m.n31; flat[offset+3] = m.n41;
		flat[offset+4] = m.n12; flat[offset+5] = m.n22; flat[offset+6] = m.n32; flat[offset+7] = m.n42;
		flat[offset+8] = m.n13; flat[offset+9] = m.n23; flat[offset+10] = m.n33; flat[offset+11] = m.n43;
		flat[offset+12] = m.n14; flat[offset+13] = m.n24; flat[offset+14] = m.n34; flat[offset+15] = m.n44;

		return flat;
	};

	public Matrix4 setTranslation(double x, double y, double z){
		return this.set(
			1, 0, 0, x,
			0, 1, 0, y,
			0, 0, 1, z,
			0, 0, 0, 1
		);
	};
	public Matrix4 setScale(double x, double y, double z){
		return this.set(
			x, 0, 0, 0,
			0, y, 0, 0,
			0, 0, z, 0,
			0, 0, 0, 1
		);
	};
	public Matrix4 setRotationX(double theta){
		double c = Math.cos(theta), s = Math.sin(theta);

		return this.set(
			1, 0,  0, 0,
			0, c, -s, 0,
			0, s,  c, 0,
			0, 0,  0, 1
		);
	};
	public Matrix4 setRotationY(double theta){
		double c = Math.cos(theta), s = Math.sin(theta);
		return this.set(
			 c, 0, s, 0,
			 0, 1, 0, 0,
			-s, 0, c, 0,
			 0, 0, 0, 1
		);
	};
	public Matrix4 setRotationZ(double theta){
		double c = Math.cos(theta), s = Math.sin(theta);
		return this.set(
			c, -s, 0, 0,
			s,  c, 0, 0,
			0,  0, 1, 0,
			0,  0, 0, 1
		);
	};
	public Matrix4 setRotationAxis(Vector3 axis, double angle){
		// Based on http://www.gamedev.net/reference/articles/article1199.asp

		double c = Math.cos(angle),
		s = Math.sin(angle),
		t = 1 - c,
		x = axis.x, y = axis.y, z = axis.z,
		tx = t*x, ty = t*y;

		return this.set(
		 	tx * x + c, tx * y - s * z, tx * z + s * y, 0,
			tx * y + s * z, ty * y + c, ty * z - s * x, 0,
			tx * z - s * y, ty * z + s * x, t * z * z + c, 0,
			0, 0, 0, 1
		);
	};
	public Matrix4 setPosition(Vector3 v){
		this.n14 = v.x;
		this.n24 = v.y;
		this.n34 = v.z;

		return this;
	};
	public Vector3 getPosition(){
		return new Vector3(this.n14, this.n24, this.n34);
	}
	
	public Vector3 getColumnX(){
		return new Vector3(this.n11, this.n21, this.n31);
	}
	public Vector3 getColumnY(){
		return new Vector3(this.n12, this.n22, this.n32);
	}
	public Vector3 getColumnZ(){
		return new Vector3(this.n13, this.n23, this.n33);
	}
	
	public Matrix4 setRotationFromEuler(Vector3 v){
		double x = v.x, y = v.y, z = v.z,
		a = Math.cos(x), b = Math.sin(x),
		c = Math.cos(y), d = Math.sin(y),
		e = Math.cos(z), f = Math.sin(z),
		ad = a * d, bd = b * d;

		this.n11 = c * e;
		this.n12 = - c * f;
		this.n13 = d;

		this.n21 = bd * e + a * f;
		this.n22 = - bd * f + a * e;
		this.n23 = - b * c;

		this.n31 = - ad * e + b * f;
		this.n32 = ad * f + b * e;
		this.n33 = a * c;

		return this;
	};
	public Matrix4 setRotationFromQuaternion(Quaternion q){
		double x = q.x, y = q.y, z = q.z, w = q.w,
		x2 = x + x, y2 = y + y, z2 = z + z,
		xx = x * x2, xy = x * y2, xz = x * z2,
		yy = y * y2, yz = y * z2, zz = z * z2,
		wx = w * x2, wy = w * y2, wz = w * z2;

		this.n11 = 1 - (yy + zz);
		this.n12 = xy - wz;
		this.n13 = xz + wy;

		this.n21 = xy + wz;
		this.n22 = 1 - (xx + zz);
		this.n23 = yz - wx;

		this.n31 = xz - wy;
		this.n32 = yz + wx;
		this.n33 = 1 - (xx + yy);

		return this;
	};
	public Matrix4 scale(Vector3 v){
		double x = v.x, y = v.y, z = v.z;

		this.n11 *= x; this.n12 *= y; this.n13 *= z;
		this.n21 *= x; this.n22 *= y; this.n23 *= z;
		this.n31 *= x; this.n32 *= y; this.n33 *= z;
		this.n41 *= x; this.n42 *= y; this.n43 *= z;

		return this;
	};
	public void extractPosition(Matrix4 m){
		this.n14 = m.n14;
		this.n24 = m.n24;
		this.n34 = m.n34;
	};
	public void extractRotation(Matrix4 m, Vector3 s){
		double invScaleX = 1 / s.x, invScaleY = 1 / s.y, invScaleZ = 1 / s.z;

		this.n11 = m.n11 * invScaleX;
		this.n21 = m.n21 * invScaleX;
		this.n31 = m.n31 * invScaleX;

		this.n12 = m.n12 * invScaleY;
		this.n22 = m.n22 * invScaleY;
		this.n32 = m.n32 * invScaleY;

		this.n13 = m.n13 * invScaleZ;
		this.n23 = m.n23 * invScaleZ;
		this.n33 = m.n33 * invScaleZ;
	};
	
	// Static
	
	public static Matrix4 makeInvert(Matrix4 m1, Matrix4 m2){

		// based on http://www.euclideanspace.com/maths/algebra/matrix/functions/inverse/fourD/index.htm

		double n11 = m1.n11, n12 = m1.n12, n13 = m1.n13, n14 = m1.n14,
		n21 = m1.n21, n22 = m1.n22, n23 = m1.n23, n24 = m1.n24,
		n31 = m1.n31, n32 = m1.n32, n33 = m1.n33, n34 = m1.n34,
		n41 = m1.n41, n42 = m1.n42, n43 = m1.n43, n44 = m1.n44;

		m2.n11 = n23*n34*n42 - n24*n33*n42 + n24*n32*n43 - n22*n34*n43 - n23*n32*n44 + n22*n33*n44;
		m2.n12 = n14*n33*n42 - n13*n34*n42 - n14*n32*n43 + n12*n34*n43 + n13*n32*n44 - n12*n33*n44;
		m2.n13 = n13*n24*n42 - n14*n23*n42 + n14*n22*n43 - n12*n24*n43 - n13*n22*n44 + n12*n23*n44;
		m2.n14 = n14*n23*n32 - n13*n24*n32 - n14*n22*n33 + n12*n24*n33 + n13*n22*n34 - n12*n23*n34;
		m2.n21 = n24*n33*n41 - n23*n34*n41 - n24*n31*n43 + n21*n34*n43 + n23*n31*n44 - n21*n33*n44;
		m2.n22 = n13*n34*n41 - n14*n33*n41 + n14*n31*n43 - n11*n34*n43 - n13*n31*n44 + n11*n33*n44;
		m2.n23 = n14*n23*n41 - n13*n24*n41 - n14*n21*n43 + n11*n24*n43 + n13*n21*n44 - n11*n23*n44;
		m2.n24 = n13*n24*n31 - n14*n23*n31 + n14*n21*n33 - n11*n24*n33 - n13*n21*n34 + n11*n23*n34;
		m2.n31 = n22*n34*n41 - n24*n32*n41 + n24*n31*n42 - n21*n34*n42 - n22*n31*n44 + n21*n32*n44;
		m2.n32 = n14*n32*n41 - n12*n34*n41 - n14*n31*n42 + n11*n34*n42 + n12*n31*n44 - n11*n32*n44;
		m2.n33 = n13*n24*n41 - n14*n22*n41 + n14*n21*n42 - n11*n24*n42 - n12*n21*n44 + n11*n22*n44;
		m2.n34 = n14*n22*n31 - n12*n24*n31 - n14*n21*n32 + n11*n24*n32 + n12*n21*n34 - n11*n22*n34;
		m2.n41 = n23*n32*n41 - n22*n33*n41 - n23*n31*n42 + n21*n33*n42 + n22*n31*n43 - n21*n32*n43;
		m2.n42 = n12*n33*n41 - n13*n32*n41 + n13*n31*n42 - n11*n33*n42 - n12*n31*n43 + n11*n32*n43;
		m2.n43 = n13*n22*n41 - n12*n23*n41 - n13*n21*n42 + n11*n23*n42 + n12*n21*n43 - n11*n22*n43;
		m2.n44 = n12*n23*n31 - n13*n22*n31 + n13*n21*n32 - n11*n23*n32 - n12*n21*n33 + n11*n22*n33;
		m2.multiplyScalar( 1 / m1.determinant() );

		return m2;
	};
	
	public static Matrix4 makeInvert(Matrix4 m1){
		return Matrix4.makeInvert(m1,new Matrix4());
	}
	
	public static Matrix3 makeInvert3x3(Matrix4 m1) throws Throwable{
		// input:  THREE.Matrix4, output: THREE.Matrix3
		// ( based on http://code.google.com/p/webgl-mjs/ )

		Matrix3 m33 = m1.m33;
		double[] m33m = m33.m;
		double a11 =   m1.n33 * m1.n22 - m1.n32 * m1.n23,
		a21 = - m1.n33 * m1.n21 + m1.n31 * m1.n23,
		a31 =   m1.n32 * m1.n21 - m1.n31 * m1.n22,
		a12 = - m1.n33 * m1.n12 + m1.n32 * m1.n13,
		a22 =   m1.n33 * m1.n11 - m1.n31 * m1.n13,
		a32 = - m1.n32 * m1.n11 + m1.n31 * m1.n12,
		a13 =   m1.n23 * m1.n12 - m1.n22 * m1.n13,
		a23 = - m1.n23 * m1.n11 + m1.n21 * m1.n13,
		a33 =   m1.n22 * m1.n11 - m1.n21 * m1.n12,

		det = m1.n11 * a11 + m1.n21 * a12 + m1.n31 * a13,

		idet;

		// no inverse
		if (det == 0) {
			throw new Exception("matrix not invertible");
		}

		idet = 1.0 / det;

		m33m[0] = idet * a11; m33m[1] = idet * a21; m33m[2] = idet * a31;
		m33m[3] = idet * a12; m33m[4] = idet * a22; m33m[5] = idet * a32;
		m33m[6] = idet * a13; m33m[7] = idet * a23; m33m[8] = idet * a33;

		return m33;
	};
	public static Matrix4 makeFrustum(double left, double right, double bottom, double top, double near, double far){
		double x, y, a, b, c, d;
		Matrix4 m = new Matrix4();
		
		x = 2*near/(right-left);
		y = 2*near/(top-bottom);
		a = (right+left)/(right-left);
		b = (top+bottom)/(top-bottom);
		c = - (far+near)/(far-near);
		d = - 2*far*near/(far-near);

		m.n11 = x;  m.n12 = 0;  m.n13 = a;   m.n14 = 0;
		m.n21 = 0;  m.n22 = y;  m.n23 = b;   m.n24 = 0;
		m.n31 = 0;  m.n32 = 0;  m.n33 = c;   m.n34 = d;
		m.n41 = 0;  m.n42 = 0;  m.n43 = - 1; m.n44 = 0;

		return m;
	};
	public static Matrix4 makePerspective(double fov, double aspect, double near, double far){
		double ymax, ymin, xmin, xmax;

		ymax = near * Math.tan(fov*Math.PI/360);
		ymin = - ymax;
		xmin = ymin * aspect;
		xmax = ymax * aspect;

		return Matrix4.makeFrustum(xmin, xmax, ymin, ymax, near, far);
	};
	public static Matrix4 makeOrtho(double left, double right, double top, double bottom, double near, double far){
		double x, y, z, w, h, p;
		Matrix4 m = new Matrix4();
		
		w = right - left;
		h = top - bottom;
		p = far - near;
		x = (right + left) / w;
		y = (top + bottom) / h;
		z = (far + near) / p;

		m.n11 = 2/w; m.n12 = 0;   m.n13 = 0;    m.n14 = -x;
		m.n21 = 0;   m.n22 = 2/h; m.n23 = 0;    m.n24 = -y;
		m.n31 = 0;   m.n32 = 0;   m.n33 = -2/p; m.n34 = -z;
		m.n41 = 0;   m.n42 = 0;   m.n43 = 0;    m.n44 = 1;

		return m;
	};
}
