package cn.com.winning.ssgj.base.id;

/**
 * @author chensj@winning.com.cn
 */
public class StepSequenceFactory extends TableSequenceFactory {

    private int step;

    private long seqNo;

    private int f;

    private long timeInMillis;

    public StepSequenceFactory() {
        setStep(10);
    }

    public void setStep(int step) {
        this.step = step;
        this.f = this.step + 1;
    }

    protected long[] internalGenerate(int idCount) {
        if (idCount > 1) {
            throw new IllegalArgumentException(
                    "step sequence cannot generate more than 1 id once.");
        }

        synchronized (this) {
            long[] result = super.internalGenerate(this.step);
            this.seqNo = result[0];
            this.timeInMillis = result[1];

            if ((this.dateCutOff) && (this.sequenceTable
                    .getTimeSerivce().isCutoff(this.cutOffType,
                            this.timeInMillis))) {
                this.f = 1;
            }

            return new long[]{this.seqNo + this.f, this.timeInMillis};
        }
    }
}