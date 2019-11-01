package com.algebra.matrices;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import java.util.Scanner;

public class Distribution {

	public static void enterMatrixData(Scanner scan, double[][] matrix, int matrixRow, int matrixCol) {
		System.out.println("Enter Matrix Data");

		for (int i = 0; i < matrixRow; i++) {
			for (int j = 0; j < matrixCol; j++) {
				matrix[i][j] = scan.nextDouble();
			}
		}
	}

	public static void main(String[] args) {

		/*Scanner scan = new Scanner(System.in);

		System.out.println("Enter The Number Of Matrix Rows for A");

		int matrixRow = scan.nextInt();

		System.out.println("Enter The Number Of Matrix Columns for A");

		int matrixCol = scan.nextInt();

		// defining matrix to set A values.
		double[][] matrixA = new double[matrixRow][matrixCol];
		// Enter Matrix Data
		enterMatrixData(scan, matrixA, matrixRow, matrixCol);

		System.out.println("Enter The Number Of Matrix Rows for C");

		int matrixRowC = scan.nextInt();

		System.out.println("Enter The Number Of Matrix Columns for C");

		int matrixColC = scan.nextInt();

		// defining matrix to set B values.
		double[][] matrixC = new double[matrixRowC][matrixColC];

		// Enter Matrix Data
		enterMatrixData(scan, matrixC, matrixRowC, matrixColC);
*/
		double[][] matrixA = { { 1,1,0,0,1}, {1,0,1,0,0 }, {1,1,1,1,0 }};
		double[][] matrixC = { { 0.65 },{0.52},{1}};


		/*
		double log1 = (Math.log10(0.2975) / Math.log10(2));
		double log2 = (Math.log10(0.24) / Math.log10(2));
		double log3 = (Math.log10(0.2225) / Math.log10(2));
		double log4 = (Math.log10(0.24) / Math.log10(2));
		
		double[][] matrixA = { {1,1,1,1,0,0,0}, { 1,0,1,0,1,0,0 },{ 0,1,1,0,0,1,0},{ 0,0,1,0,0,0,1}  };
		double[][] matrixC = { { log1 },{log2},{log3},{log4} };
		
		double[][] matrixA = { { 0.2975,0.2775,0.2225,0.2025}};
		double[][] matrixC = { {-1.703385};
		*/
		
		// Creating a matrix with type RealMatrix.
		RealMatrix temp = MatrixUtils.createRealMatrix(matrixA);
		// s is a singular decomposition of A matrix. A = U * S * VT
		SingularValueDecomposition s = new SingularValueDecomposition(temp);

		// Instance of SVD object in order to manage my own objects.
		SVD svd = new SVD();
		svd.setA(matrixA);
		svd.setC(matrixC);

		svd.setV(s.getV());
		svd.setU(s.getU());
		svd.setS(s.getS());
		// Create S-1 diagonal from singular values. If I have a 2*4 A matrix then I
		// would have to create a 4*2 matrix for S-1
		double[] sv = s.getSingularValues();
		s.getV().getColumnDimension();
		//matrixRow = Enter The Number Of Matrix Rows for A
		//svd.setSigmaInvert(s.getV().getColumnDimension(), matrixRow, sv);
		svd.setSigmaInvert(s.getV().getColumnDimension(), 3, sv);
		

		double[][] tempTransp = svd.transpose(svd.getU());
		svd.setUT(tempTransp);

		tempTransp = svd.transpose(svd.getV());
		svd.setVT(tempTransp);

		System.out.println("Original Matrix A");
		svd.printMatrix(svd.getA());
		System.out.println();

		System.out.println("Matrix S");
		svd.printMatrix(svd.getS());
		System.out.println();

		System.out.println("Matrix S^-1");
		svd.printMatrix(svd.getSigmaInvert());
		System.out.println();

		System.out.println("Matrix U");
		svd.printMatrix(svd.getU());
		System.out.println();

		System.out.println("Matrix V");
		svd.printMatrix(svd.getV());
		System.out.println();

		System.out.println();
		System.out.println("Matrix UT");
		svd.printMatrix(svd.getUT());
		System.out.println();

		System.out.println();
		System.out.println("Matrix VT");
		svd.printMatrix(svd.getVT());
		System.out.println();

		// Obtaining results P = V * S^-1 * UT * C
		double[][] product = svd.multiplyMatrices(svd.getV(), svd.getSigmaInvert());
		double[][] product2 = svd.multiplyMatrices(product, svd.getUT());
		double[][] product3 = svd.multiplyMatrices(product2, svd.getC());
		
		System.out.println ("The local optimal solution x: ");
		svd.printMatrix(product3);
		
		//STEP 5 and 6
		double[][] b = { { 0.65,0.52,1}}; //B TRANSPOSE
		double[][] d = { { -0.2429},{-0.2975},{-1.3908},{0.07746} }; // DELTA
		
		System.out.println (" ");
		System.out.println ("btDELTA ");
		svd.printMatrix(svd.multiplyMatrices (b,d));
		
		//STEP 7 and 8
		double c1 = (Math.log10(0.2975) / Math.log10(2));
		double c2 = (Math.log10(0.2775) / Math.log10(2));
		double c3 = (Math.log10(0.2225) / Math.log10(2));
		double c4 = (Math.log10(0.2025) / Math.log10(2));
		
		double[][] c = { { c1,c2,c3,c4}};
		double[][] x = { {0.2975}, {0.2775}, {0.2225}, {0.2025} };
		
		System.out.println (" ");
		System.out.println ("cT x ");
		svd.printMatrix(svd.multiplyMatrices (c,x));
		
		//Proving solutions Y1 and Y2
		/*System.out.println ("Second solution: ");
		double[][] b = { { 0.704900004},{-0.289399998},{-0.184899997},{0.769400001},{0.234500001} };
		

		svd.printMatrix(svd.multiplyMatrices (matrix,b));
		
		System.out.println ("Third solution: ");
		double[][] c = { { -0.616899996},{-0.185899998},{1.136900003},{0.665900001},{1.452800001} };
		
		
		svd.printMatrix(svd.multiplyMatrices (matrix,c));*/
		
		
		

	}

}
