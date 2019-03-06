import java.util.Arrays;
import java.util.Random;

public class test {
	public static final int WEEKS = 10;
	public static final double BETA = 0.2;
	
	public static void main(String[] args) {
		double[] quality = new double[10];
		double[] initialQuality = getQuality(taQuality);
		double[] estimate = new double[10];
		double[] initialEstimate = getEstimate(estimate);
		for (int i = 0; i < WEEKS; i++) {
			double[] alpha = Calculate.calculateAlpha(estimate, BETA);
			estimate = Calculate.calculateEstimate(quality, estimate, alpha);
		}
		System.out.println("Initial estimates: " + Arrays.toString(initialEstimate));
		System.out.println("Final estimates: " + Arrays.toString(initialEstimate));
		
	}
	
	
	public double[] getQuality(double[] copy) {
		double[] ta = new double[10];
		for (int i = 0; i < 9; i++) {
			ta[i] = i / 1.;
			copy[i] = i / 1.;
		}
		return ta;
	}
	
	public double[] getEstimate(double[] copy) {
		double[] estimates = new double[10];
		double standardDev = 0.2;
		Random r = new Random();
		for (int i = 0; i < 9; i++) {
			estimates[i] = r.nextGaussian() * standardDev + (i / 1.);
			copy[i] = estimates[i];
		}
		return estimates;
	}
	
	
}
