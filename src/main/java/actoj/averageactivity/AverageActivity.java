package actoj.averageactivity;

import actoj.core.Actogram;

public class AverageActivity {

	/**
	 * Calculate the average activity pattern of an actogram.
	 * @param acto The actogram of which the activity pattern is calculated.
	 * @param fromData The index in the data which is used as the interval start.
	 * @param toData The index in the data which is used as the interval end (exclusively).
	 * @param period The assumed period length in sample units.
	 * @return
	 */
	public static AverageActivity calculateAverageActivity(Actogram acto, int fromData, int toData, int period) {
		return new AverageActivity(acto, fromData, toData, period);
	}

	private final float[] averageActivities;
	private final int[] counts;
	
	private float meanVal = 0;
	private float meanValWithoutZero = 0;

	private AverageActivity(Actogram acto, int fromData, int toData, int period) {

		this.averageActivities = new float[period];
		this.counts = new int[period];

		float[] data = acto.getData();

		for(int i = fromData; i < toData; i++) {
			int idx = i % period;
			counts[idx]++;
			averageActivities[idx] += data[i];
		}

		for(int i = 0; i < period; i++)
			averageActivities[i] /= counts[i];

		calculateMeans();
	}

	public float[] getAverageActivity() {
		return averageActivities;
	}

	private void calculateMeans() {
		if (averageActivities != null && averageActivities.length > 0) {
			
			
			int count = 0;
			int countWithoutZero = 0;
			meanVal = 0;
			meanValWithoutZero = 0;
			for (int i = 0; i < averageActivities.length; i++) {
				
				float x = averageActivities[i];
				if (x >=0) {
					if (x > 0) {
						countWithoutZero++;
						meanValWithoutZero += x;
					}
					count++;
					meanVal += x;
				}
			}
			if (count > 0)
				meanVal = meanVal / count;
			if (countWithoutZero > 0)
				meanValWithoutZero = meanValWithoutZero / countWithoutZero;

		}
	}

	public float getMeanVal() {
		return meanVal;
	}

	public float getMeanValWithoutZero() {
		return meanValWithoutZero;
	}
	
	
}

