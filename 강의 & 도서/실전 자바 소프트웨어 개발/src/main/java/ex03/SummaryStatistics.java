package ex03;

public class SummaryStatistics {
	private double sum;
	private double max;
	private double min;
	private double average;
	
	
	public SummaryStatistics() {
	}
	
	public SummaryStatistics(double sum, double max, double min, double average) {
		this.sum = sum;
		this.max = max;
		this.min = min;
		this.average = average;
	}
	
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
}
