package com.google.code.gwt.threejs.client.core;

public final class Quaternion {
	public Quaternion(){
		this(0,0,0,1);
	}
	public Quaternion(double x, double y, double z){
		this.set(x, y, z, 1);
	}

	public Quaternion(double x, double y, double z, double w){
		this.set(x, y, z, w);
	}

	// Properties
	protected double x,y,z,w;
	
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}
	public double getZ(){
		return this.z;
	}
	public double getW(){
		return this.w;
	}
	
	// Methods
	
	public Quaternion set(double x, double y, double z, double w){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;

		return this;
	}
	
	public Quaternion copy(Quaternion q){
		return this.set(q.x, q.y, q.z, q.w);
	}
	
	public Quaternion setFromEuler(Vector3 vec3){
		double c = 0.5 * Math.PI / 360, // 0.5 is an optimization
		x = vec3.x * c,
		y = vec3.y * c,
		z = vec3.z * c,

		c1 = Math.cos(y),
		s1 = Math.sin(y),
		c2 = Math.cos(-z),
		s2 = Math.sin(-z),
		c3 = Math.cos(x),
		s3 = Math.sin(x),

		c1c2 = c1 * c2,
		s1s2 = s1 * s2;

		double tw = c1c2 * c3  - s1s2 * s3,
	  	tx = c1c2 * s3  + s1s2 * c3,
		ty = s1 * c2 * c3 + c1 * s2 * s3,
		tz = c1 * s2 * c3 - s1 * c2 * s3;
		
		return this.set(tx, ty, tz, tw);
	}
	public Quaternion setFromAxisAngle(Vector3 axis, double angle){
		// from http://www.euclideanspace.com/maths/geometry/rotations/conversions/angleToQuaternion/index.htm
		// axis have to be normalized

		double halfAngle = angle / 2,
			s = Math.sin(halfAngle);

		return this.set(axis.x * s, axis.y * s, axis.z * s, Math.cos(halfAngle));
	}
	public Quaternion calculateW(){
		this.w = - Math.sqrt(Math.abs(1.0 - this.x * this.x - this.y * this.y - this.z * this.z));

		return this;
	}
	public Quaternion inverse(){
		this.x *= -1;
		this.y *= -1;
		this.z *= -1;

		return this;
	}
	public double length(){
		return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
	}
	public Quaternion normalize(){
		double l = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w);
		if (l == 0) {

			this.x = 0;
			this.y = 0;
			this.z = 0;
			this.w = 0;

		} else {

			l = 1 / l;

			this.x = this.x * l;
			this.y = this.y * l;
			this.z = this.z * l;
			this.w = this.w * l;

		}

		return this;
	}
	public Quaternion multiplySelf(Quaternion quat2){
		double qax = this.x,  qay = this.y,  qaz = this.z,  qaw = this.w,
		qbx = quat2.x, qby = quat2.y, qbz = quat2.z, qbw = quat2.w;

		double tx = qax * qbw + qaw * qbx + qay * qbz - qaz * qby,
		ty = qay * qbw + qaw * qby + qaz * qbx - qax * qbz,
		tz = qaz * qbw + qaw * qbz + qax * qby - qay * qbx,
		tw = qaw * qbw - qax * qbx - qay * qby - qaz * qbz;

		return this.set(tx, ty, tz, tw);
	}
	public Quaternion multiply(Quaternion q1, Quaternion q2){
		// from http://www.euclideanspace.com/maths/algebra/realNormedAlgebra/quaternions/code/index.htm

		double tx =  q1.x * q2.w + q1.y * q2.z - q1.z * q2.y + q1.w * q2.x,
		ty = -q1.x * q2.z + q1.y * q2.w + q1.z * q2.x + q1.w * q2.y,
		tz =  q1.x * q2.y - q1.y * q2.x + q1.z * q2.w + q1.w * q2.z,
		tw = -q1.x * q2.x - q1.y * q2.y - q1.z * q2.z + q1.w * q2.w;

		return this.set(tx,ty,tz,tw);
	}
	public Vector3 multiplyVector3(Vector3 v){
		return this.multiplyVector3(v, v);
	}
	public Vector3 multiplyVector3(Vector3 v, Vector3 destination){
		double x = v.x, y = v.y, z = v.z,
		qx = this.x, qy = this.y, qz = this.z, qw = this.w;

		// calculate quat * vec

		double ix =  qw * x + qy * z - qz * y,
			iy =  qw * y + qz * x - qx * z,
			iz =  qw * z + qx * y - qy * x,
			iw = -qx * x - qy * y - qz * z;

		// calculate result * inverse quat

		destination.x = ix * qw + iw * -qx + iy * -qz - iz * -qy;
		destination.y = iy * qw + iw * -qy + iz * -qx - ix * -qz;
		destination.z = iz * qw + iw * -qz + ix * -qy - iy * -qx;

		return destination;
	}
	
	// Statics
	
	public static Quaternion slerp(Quaternion qa, Quaternion qb, Quaternion qm, double t){
		double cosHalfTheta = qa.w * qb.w + qa.x * qb.x + qa.y * qb.y + qa.z * qb.z;

		if (Math.abs(cosHalfTheta) >= 1.0) {

			qm.w = qa.w; qm.x = qa.x; qm.y = qa.y; qm.z = qa.z;
			return qm;

		}

		double halfTheta = Math.acos(cosHalfTheta),
		sinHalfTheta = Math.sqrt(1.0 - cosHalfTheta * cosHalfTheta);

		if (Math.abs(sinHalfTheta) < 0.001) { 

			qm.w = 0.5 * (qa.w + qb.w);
			qm.x = 0.5 * (qa.x + qb.x);
			qm.y = 0.5 * (qa.y + qb.y);
			qm.z = 0.5 * (qa.z + qb.z);

			return qm;

		}

		double ratioA = Math.sin((1 - t) * halfTheta) / sinHalfTheta,
		ratioB = Math.sin(t * halfTheta) / sinHalfTheta; 

		qm.w = (qa.w * ratioA + qb.w * ratioB);
		qm.x = (qa.x * ratioA + qb.x * ratioB);
		qm.y = (qa.y * ratioA + qb.y * ratioB);
		qm.z = (qa.z * ratioA + qb.z * ratioB);

		return qm;
	}
}
