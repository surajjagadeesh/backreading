import java.util.Random;

public class Calculate {

    public static Double[] calculateAlpha(Double[] estimate, Double beta) {
        Double sum = 0.0;
        for (int i = 0; i < estimate.length; i++) {
            sum += (1 - estimate[i]);
        }

        Double[] alphas = new Double[estimate.length];
        for (int i = 0; i < alphas.length; i++) {
            alphas[i] =  (1 - estimate[i]) / sum * beta * (estimate.length);
        }
        return alphas;
    }

    private static Double calculateScore(Double trueQuality, Double alpha) {
        Random r = new Random();
        double score = r.nextGaussian() * (1.0 / alpha) + trueQuality;
        return score;
    }

    public static Double[] calculateEstimate(Double[] trueQuality, Double[] estimate, Double alpha, Double score) {
        Double[] updates = new Double[estimate.length];
        for (int i = 0; i < updates.length; i++) {
            updates[i] = estimate[i] + (calculateScore(trueQuality[i], alpha) - estimate[i]) * alpha;
        }
        return updates;
    }
}