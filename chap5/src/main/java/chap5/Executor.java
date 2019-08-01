package chap5;

public class Executor {
	private Worker worker;
	public void addUnit(WorkUnit unit) {
		worker.work(unit);
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
		
	}
}
