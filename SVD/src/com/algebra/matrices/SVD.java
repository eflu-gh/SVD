package com.algebra.matrices;
import org.apache.commons.math3.linear.RealMatrix;

public class SVD {
	private double A[][];
	private double C[][];
	private double V[][];
	private double U[][];
	private double UT[][];
	private double VT[][];
	private double[][] S;
	private double[][] SI;

	public void printMatrix(double[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public void setA(double[][] a) {
		A = a;
	}

	public double[][] getA() {
		return A;
	}

	public void setC(double[][] c) {
		C= c;
	}

	public double[][] getC() {
		return C;
	}

	public void setUT(double[][] ut) {
		UT = ut;
	}

	public double[][] getUT() {
		return UT;
	}

	public void setVT(double[][] vt) {
		VT = vt;
	}

	public double[][] getVT() {
		return VT;
	}

	public void setSigmaInvert(int matrixRow, int matrixCol, double[] sv) {

		SI = new double[matrixRow][matrixCol];

		for (int i = 0; i < matrixRow; i++) {
			for (int j = 0; j < matrixCol; j++) {
				if (i == j)
					SI[i][j] = (float) (1.0 / sv[j]);
				else
					SI[i][j] = 0;

			}

		}

	}

	public double[][] getSigmaInvert() {
		return SI;
	}

	public void setV(RealMatrix X) {

		V = new double[X.getRowDimension()][X.getColumnDimension()];

		for (int i = 0; i < X.getRowDimension(); i++) {
			for (int j = 0; j < X.getColumnDimension(); j++) {
				V[i][j] = X.getEntry(i, j);

			}

		}
	}

	public double[][] getV() {
		return V;
	}

	public void setU(RealMatrix X) {

		U = new double[X.getRowDimension()][X.getColumnDimension()];

		for (int i = 0; i < X.getRowDimension(); i++) {
			for (int j = 0; j < X.getColumnDimension(); j++) {
				U[i][j] = X.getEntry(i, j);
			}

		}
	}

	public double[][] getU() {
		return U;
	}

	public double[][] transpose(double matrixT[][]) {
		int row = matrixT.length;
		int col = matrixT[0].length;

		double transpose[][] = null;
		try {
			transpose = new double[col][row];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					transpose[j][i] = matrixT[i][j];
				}
			}
		} catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("Something happened with dimensions of matrices...");
		}

		catch (Exception e) {

			System.out.println("Something happened with dimensions of matrices...");
		}

		return transpose;
	}

	public double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
		int r1 = firstMatrix.length;
		int c1 = firstMatrix[0].length;
		int c2 = secondMatrix[0].length;

		double[][] product = null;
		try {
			product = new double[r1][c2];
			for (int i = 0; i < r1; i++) {
				for (int j = 0; j < c2; j++) {
					for (int k = 0; k < c1; k++) {
						product[i][j] += (firstMatrix[i][k] * secondMatrix[k][j]);
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException a) {
			System.out.println("Something happened with dimensions of matrices...");
		}

		catch (Exception e) {

			System.out.println("Something happened with dimensions of matrices...");
		}

		return product;
	}

	public void setS(RealMatrix X) {

		S = new double[X.getRowDimension()][X.getColumnDimension()];

		for (int i = 0; i < X.getRowDimension(); i++) {
			for (int j = 0; j < X.getColumnDimension(); j++) {
				S[i][j] = X.getEntry(i, j);

			}

		}
	}

	public double[][] getS() {
		return S;
	}

}
