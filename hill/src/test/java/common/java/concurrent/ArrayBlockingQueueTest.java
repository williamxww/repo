package common.java.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author vv
 * 模拟一个线程解析文件多个线程入库
 */
public class ArrayBlockingQueueTest {

	private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);
	
	private void startReading(){
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					Thread.sleep(10);
					//满了就一直等着
					blockingQueue.put("aaa");
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		},"reading");
		
		t.start();
	}
	
	private void startStoring() {
		// 线程数小于5时直接新增线程处理任务,当线程数达到5后则用SynchronousQueue存储任务,
		// 当queue满了则又新增线程来处理任务,当线程数达到最高值100则采用丢弃策略
		// SynchronousQueue 插入取出插入取出...必须交替进行（放一个就满了）
		// LinkedBlockingQueue无界队列任务会一直往里面添加知道撑爆，最大线程数此时相当于无效
		ExecutorService threadPool = new ThreadPoolExecutor(5, 100,
				20L, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(10));
		Runnable store = new Runnable() {

			@Override
			public void run() {
				
			}
			
		};
	}
}
