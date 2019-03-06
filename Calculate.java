import java.util.Random;

public class Calculate {
    
    public static double[] calculateAlpha(double[] estimate, double beta) {
	
	// summation of i = 1 to n 1 - (q_i hat at time t)
        double sum = 0.0;
        for (int i = 0; i < estimate.length; i++) {
            sum += (1 - estimate[i]);
        }

        double[] alphas = new double[estimate.length];
        for (int i = 0; i < alphas.length; i++) {
	    // alpha = (1 - (q_i hat at time t)) / summation * beta * n
            alphas[i] =  (1 - estimate[i]) / sum * beta * (estimate.length);
        }
        return alphas;
    }

    private static double calculateScore(double trueQuality, double alpha) {
        Random r = new Random();
        
        // generate scores with N(mean = trueQuality, var = 1/alpha)
        double score = r.nextGaussian() * (1.0 / alpha) + trueQuality;
        return score;
    }

    public static double[] calculateEstimate(double[] trueQuality, double[] estimate, double[] alphas) {
        double[] updates = new double[estimate.length];
        for (int i = 0; i < updates.length; i++) {
	    // new estimate = old estimate + (score - old estimate) * alpha_i
            updates[i] = estimate[i] + (calculateScore(trueQuality[i], alphas[i]) - estimate[i]) * alphas[i];
        }
        return updates;
    }
}