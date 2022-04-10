package FOO;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * multitasking cook using multiple threads to perform multiple tasks
 */
public class Cook {

	/**
	 * 2d array to hold 2 semaphores per task, both of which will need to be acquired to start
	 */
	Semaphore[][] semArr = new Semaphore[9][2];
	/**
	 * Array holding all individual task regrouping them all
	 */
	Task[] tasks = new Task[9];

	/**
	 * constructs object by filling array with semaphores, removing permits from task needing
	 * conclusion of other task and creating array of tasks
	 */
	public Cook() {

		//fill array with 1 permit semaphore
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 2; j++) {
				semArr[i][j] = new Semaphore(1);
			}
		}

		//Remove semaphore for tasks who need to wait and will be restored by the specific task
		semArr[3][0].drainPermits();
		semArr[3][1].drainPermits();
		semArr[5][0].drainPermits();
		semArr[5][1].drainPermits();
		semArr[6][0].drainPermits();
		semArr[6][1].drainPermits();
		semArr[8][0].drainPermits();
		semArr[8][1].drainPermits();

		//initializes the array with one of each tasks
		tasks[0] = new Task1();
		tasks[1] = new Task2();
		tasks[2] = new Task3();
		tasks[3] = new Task4();
		tasks[4] = new Task5();
		tasks[5] = new Task6();
		tasks[6] = new Task7();
		tasks[7] = new Task8();
		tasks[8] = new Task9();
	}

	/**
	 * Start thread of all tasks in array tasks
	 */
	public void start() {
		for (Task task : tasks)
			task.start();
	}

	/**
	 * Abstract class to be used to build multithreaded cook recipe
	 */
	abstract class Task implements Runnable {

		/**
		 * Thread to be created and ran when start called
		 */
		Thread t;

		/**
		 * if no thread has already been created, creates one and run it, else does nothing
		 */
		public void start(){
			if(t == null){
				t = new Thread(this);
				t.start();
			}
		}

		/**
		 * contains the code ran by thread
		 */
		abstract public void run();
	}

	/**
	 * Task containing thread to be ran in conjunction with other task of cook class
	 */
	class Task1 extends Task {

		public Task1() {
		}

		/**
		 * Starts immediately, prints starting message, waits and releases one of task 4s semaphore
		 */
		@Override
		public void run() {
			try {
				semArr[0][0].acquire();
				semArr[0][1].acquire();
				System.out.println("Starting task1");
				Thread.sleep(6000);
				semArr[3][0].release();
				System.out.println("Finished task1");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Task containing thread to be ran in conjunction with other task of cook class
	 */
	class Task2 extends Task {
		public Task2() {
		}

		/**
		 * Starts immediately, prints starting message, waits and releases one of task 4s semaphore
		 */
		@Override
		public void run() {
			try {
				semArr[1][0].acquire();
				semArr[1][1].acquire();
				System.out.println("Starting task2");
				Thread.sleep(5000);
				semArr[3][1].release();
				System.out.println("Finished task2");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Task containing thread to be ran in conjunction with other task of cook class
	 */
	class Task3 extends Task {
		public Task3() {
		}

		/**
		 * Starts immediately, prints starting message, waits and releases one of task 6s semaphore
		 */
		@Override
		public void run() {
			try {
				semArr[2][0].acquire();
				semArr[2][1].acquire();
				System.out.println("Starting task3");
				Thread.sleep(3000);
				semArr[5][0].release();
				System.out.println("Finished task3");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Task containing thread to be ran in conjunction with other task of cook class
	 */
	class Task4 extends Task {
		public Task4() {
		}

		/**
		 * wait for task 1 and 2 completion print starting message,
		 * waits and releases one of task 6s semaphore
		 */
		@Override
		public void run() {
			try {
				semArr[3][0].acquire();
				semArr[3][1].acquire();
				System.out.println("Starting task4");
				Thread.sleep(7000);
				semArr[5][1].release();
				System.out.println("Finished task4");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Task containing thread to be ran in conjunction with other task of cook class
	 */
	class Task5 extends Task {
		public Task5() {
		}

		/**
		 * Starts immediately, prints starting message, waits and releases one of task 7s semaphore
		 */
		@Override
		public void run() {
			try {
				semArr[4][0].acquire();
				semArr[4][1].acquire();
				System.out.println("Starting task5");
				Thread.sleep(8000);
				semArr[6][0].release();
				System.out.println("Finished task5");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Task containing thread to be ran in conjunction with other task of cook class
	 */
	class Task6 extends Task {
		public Task6() {
		}

		/**
		 * Waits for completion of task 3 and 4, prints starting message,
		 * waits and release one of task 7s semaphore
		 */
		@Override
		public void run() {
			try {
				semArr[5][0].acquire();
				semArr[5][1].acquire();
				System.out.println("Starting task6");
				Thread.sleep(2000);
				semArr[6][1].release();
				System.out.println("Finished task6");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Task containing thread to be ran in conjunction with other task of cook class
	 */
	class Task7 extends Task {
		public Task7() {
		}

		/**
		 * waits for completion of task 5 and 6, print starting message,
		 * waits and releases one of task 9s semaphore
		 */
		@Override
		public void run() {
			try {
				semArr[6][0].acquire();
				semArr[6][1].acquire();
				System.out.println("Starting task7");
				Thread.sleep(3000);
				semArr[8][0].release();
				System.out.println("Finished task7");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Task containing thread to be ran in conjunction with other task of cook class
	 */
	class Task8 extends Task {
		public Task8() {

		}

		/**
		 * Start immediately, print starting message, wait and release one of task 9s semaphore
		 */
		@Override
		public void run() {
			try {
				semArr[7][0].acquire();
				semArr[7][1].acquire();
				System.out.println("Starting task8");
				Thread.sleep(5000);
				semArr[8][1].release();
				System.out.println("Finished task8");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Task containing thread to be ran in conjunction with other task of cook class
	 */
	class Task9 extends Task {
		public Task9() {
		}

		/**
		 * waits for completion of task 7 and 8, print starting message,
		 * waits and prints final message of program
		 */
		@Override
		public void run() {

			try {
				semArr[8][0].acquire();
				semArr[8][1].acquire();
				System.out.println("Starting task9");
				Thread.sleep(2000);
				System.out.println("Finished task9, recipe finished!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
