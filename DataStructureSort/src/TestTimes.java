public class TestTimes implements TestTimesInterface {
	private int index = 0;
	private long[] tt = new long[10];
	private double[] MU = new double[10];
	private int T;
	private int B;

	TestTimes() {
		B = 1;
		T = 1;
		index = 0;
		tt = new long[10];
		for (int x = 0; x < tt.length; x++) {
			tt[x] = 0;
		}
		MU = new double[10];
		for (int x = 0; x < MU.length; x++) {
			MU[x] = 0;
		}
	}

	@Override
	public void addTestTime(long testTime) {
		double Bytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (index < tt.length) { // 0< 10
			tt[index] = (long) (testTime/this.T); // index = 0,1,2,3,4,5,6,7,8,9
			MU[index] = Bytes/B;
			index++;
		} else {
			for (int i = 0; i < (tt.length - 1); i++) {
				tt[i] = tt[i + 1];
				MU[i] = MU[i+1];
			}
			tt[9] = (long) (testTime/T);
			MU[9] = Bytes/B;
		}
		
	}

	@Override
	public double getLastTestTime() {

		if (this.index > 0) {
			return tt[index - 1];
		} else {
			return 0.0;
		}
	}

	@Override
	public double[] getTestTimes() {
		double[] newTT = new double[10];
		for (int i = 0; i < tt.length; i++) {
			newTT[i] = tt[i];
		}
		for(int i = 0; i<tt.length;i++) {
			System.out.println(newTT[i]);
		}
		

		return newTT;
	}

	@Override
	public void resetTestTimes() {
		for (int i = 0; i < tt.length; i++) {
			tt[i] = 0;
		}

	}

	@Override
	public double getAverageTestTime() {
		double sum = 0;
		for (int i = 0; i < tt.length; i++) {
			sum = sum + tt[i];
		}
		sum = sum / index;
		return sum;
	}

	@Override
	public TimeUnits getTimeUnits() {
		if (this.T==1/1000000000){
			return TimeUnits.Seconds;
		}
		if (this.T==1/1000){
			return TimeUnits.MilliSeconds;
		}
		if (this.T==1/1000000){
			return TimeUnits.MicroSeconds;
		}
		else if (this.T==1){
			return TimeUnits.NanoSeconds;
		}
		return null;
	}

	@Override
	public void setTimeUnits(TimeUnits timeUnits) {
		switch(timeUnits){
		case Seconds:
			this.T = 1000000000;
			for(int i = 0; i<tt.length;i++){
				tt[i]= tt[i]*1/1000000000;
			}
			break;
		case MilliSeconds:
			this.T=1000000;
			for(int i = 0; i<tt.length;i++){
				tt[i]= tt[i]*1/1000000;
			}
			break;
		case MicroSeconds:
			this.T = 1000;
			for(int i = 0; i<tt.length;i++){
				tt[i]= tt[i]*1/1000;
			}
			break;
		case NanoSeconds:
			this.T = 1;
			for(int i = 0; i<tt.length;i++){
				tt[i]= tt[i]*1000;
			}
			break;
		}
	}

	@Override
	public MemoryUnits getMemoryUnits() {
		if (this.B==1){
			return MemoryUnits.Bytes;
		}
		if (this.B==1024){
			return MemoryUnits.KiloBytes;
		}
		else if (this.B==1048576){
			return MemoryUnits.MegaBytes;
		}
		return null;
	}

	@Override
	public void setMemoryUnits(MemoryUnits memoryUnits) {
		switch(memoryUnits){
		case Bytes:
			this.B = 1;
			for (int i = 0; i< MU.length;i++){
				MU[i] = MU[i]/1;
			}
			break;
		case KiloBytes:
			this.B=1024;
			for (int i = 0; i< MU.length;i++){
				MU[i] = MU[i]/1024;
			}
			break;
		case MegaBytes:
			this.B = 1048576;
			for (int i = 0; i< MU.length;i++){
				MU[i] = MU[i]/1024;
			}
			break;
		}
	}

	@Override
	public double getLastMemoryUsage() {

		if (this.index > 0) {
			return MU[index - 1];
		} else {
			return 0.0;
		}
	}

	@Override
	public double[] getMemoryUsages() {
		double[] newMU = new double[10];
		for (int i = 0; i < MU.length; i++) {
			newMU[i] = MU[i];
		}
		for(int i = 0; i<10; i++) {
			System.out.println(newMU[i]);
		}
		return newMU;
	}

	@Override
	public double getAverageMemoryUsage() {
		double sum = 0;
		double x = index;
		for (int i = 0; i < MU.length; i++) {
			sum = sum + MU[i];
		}
		sum=sum/x;
		return sum;
	}
}