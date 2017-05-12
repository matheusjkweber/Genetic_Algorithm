package Genetic_Algorithm;

import java.io.Serializable;

public class Config implements Serializable {
	private float p_mass; // specific mass
	private float p1; // R$/kWh - grecusto do kwh em tarifa verde
	private float p2; // R$/kWh - custo do kw de demanda
	private float p3; // R$/kW - custo do kwh horario reservado
	private float p4; // R$/kg - custo do kg da chapa de aço
	private float rend; 
	private float n_h; 
	private int n; // Years
	private float tax; // %
	private float PIS; // tax
	private float COFINS; // tax
	private float FRC; // tax
	private float ICMS; // tax
	private float rev_p1; // revised p1
	private float rev_p2; // revised p2
	private float rev_p3; // revised p3;
	
	private float c;
	private float n1;
	
	public Config(float p1, float p2, float p3, float p4,
			float rend, float n_h, int n, float tax, float PIS, float COFINS, float ICMS) {
		this.p_mass = (float) 21.2;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.p4 = p4;
		this.rend = rend;
		this.n_h = n_h;
		this.n = n;
		this.tax = tax;
		PIS = PIS;
		COFINS = COFINS;
		ICMS = ICMS;
		
		float a = (float) (0.01 * tax * Math.pow((1 + 0.01 * this.tax),n));
		float b = (float) (Math.pow(1 + 0.1 * tax, n) -1);
		this.FRC = a / b;
		
		ICMS = 12;
		
		rev_p1 = p1 / (1 - (PIS/100) - (COFINS/100) - (ICMS/100));
		rev_p2 = p2 / (1 - (PIS/100) - (COFINS/100) - (ICMS/100));
		rev_p3 = p3 / (1 - (PIS/100) - (COFINS/100) - (ICMS/100));
		
		this.c = 90;
		this.n1 = 120;
		this.n = n;
	}
	
	public float getP_mass() {
		return p_mass;
	}

	public void setP_mass(float p_mass) {
		this.p_mass = p_mass;
	}

	public float getP1() {
		return p1;
	}

	public void setP1(float p1) {
		this.p1 = p1;
	}

	public float getP2() {
		return p2;
	}

	public void setP2(float p2) {
		this.p2 = p2;
	}

	public float getP3() {
		return p3;
	}

	public void setP3(float p3) {
		this.p3 = p3;
	}

	public float getP4() {
		return p4;
	}

	public void setP4(float p4) {
		this.p4 = p4;
	}

	public float getRend() {
		return rend;
	}

	public void setRend(float rend) {
		this.rend = rend;
	}

	public float getN_h() {
		return n_h;
	}

	public void setN_h(float n_h) {
		this.n_h = n_h;
	}

	public int getN() {
		return n;
	}

	public float getN1() {
		return n1;
	}

	public void setN(int n) {
		this.n = n;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public float getPIS() {
		return PIS;
	}

	public void setPIS(float pIS) {
		PIS = pIS;
	}

	public float getCOFINS() {
		return COFINS;
	}

	public void setCOFINS(float cOFINS) {
		COFINS = cOFINS;
	}

	public float getFRC() {
		return FRC;
	}

	public void setFRC(float fRC) {
		FRC = fRC;
	}

	public float getICMS() {
		return ICMS;
	}

	public void setICMS(float iCMS) {
		ICMS = iCMS;
	}

	public float getRev_p1() {
		return rev_p1;
	}

	public void setRev_p1(float rev_p1) {
		this.rev_p1 = rev_p1;
	}

	public float getRev_p2() {
		return rev_p2;
	}

	public void setRev_p2(float rev_p2) {
		this.rev_p2 = rev_p2;
	}

	public float getRev_p3() {
		return rev_p3;
	}

	public void setRev_p3(float rev_p3) {
		this.rev_p3 = rev_p3;
	}

	public Config(){
		p_mass = (float) 21.2;
		p1 = (float) 0.33;
		p4 = (float) 2.99;
		rend = (float) 0.60;
		n_h = 1800;
		p2 = (float) 9.15;
		n = 10;
		tax = 12;
		p3 = (float) 0.09982;
		PIS = (float) 0.817678;
		COFINS = (float) 3.774126;
		
		float a = (float) (0.01 * tax * Math.pow((1 + 0.01 * this.tax),n)); //(0.01*$K$2*(1+0.01*$K$2)^$K$1)
		float b = (float) (Math.pow(1 + 0.01 * tax, n) -1);
		FRC = a / b;
		
		ICMS = 12;
		
		rev_p1 = p1 / (1 - (PIS/100) - (COFINS/100) - (ICMS/100));
		rev_p2 = p2 / (1 - (PIS/100) - (COFINS/100) - (ICMS/100));
		rev_p3 = p3 / (1 - (PIS/100) - (COFINS/100) - (ICMS/100));
		
		c = 90;
		n1 = 120;
	}

	public void setN1(float n) {
		this.n1 = n;
	}

	public float getC() {
		return c;
	}

	public void setC(float c) {
		this.c = c;
	}
	
}
