package com.stockbone.utils.concurrent.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * 线程安全的ArrayList
 * <p>
 * java.util.ArrayList 非线程安全
 * </p>
 * 
 * @param <E>
 * 
 */
public class ConcurrentArrayList<E> extends ArrayList<E> {

	private static final long serialVersionUID = -3122108953867681008L;

	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private WriteLock writeLock = lock.writeLock();
	private ReadLock readLock = lock.readLock();

	@Override
	public boolean add(E e) {
		boolean result = true;
		writeLock.lock();
		try {
			super.add(e);
		} catch (Exception exception) {
			result = false;
		} finally {
			writeLock.unlock();
		}
		return result;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean result = true;
		writeLock.lock();
		try {
			super.addAll(c);
		} catch (Exception e) {
			result = false;
		} finally {
			writeLock.unlock();
		}
		return result;
	}

	@Override
	public E get(int index) {
		E result = null;
		readLock.lock();
		try {
			result = super.get(index);
		} catch (Exception e) {
			
		} finally {
			readLock.unlock();
		}
		return result;
	}

	@Override
	public E remove(int index) {
		E result = null;
		readLock.lock();
		try {
			result = super.remove(index);
		} catch (Exception e) {
			
		} finally {
			readLock.unlock();
		}
		return result;
	}

	@Override
	public boolean remove(Object o) {
		boolean result = true;
		writeLock.lock();
		try {
			result = super.remove(o);
		} catch (Exception e) {
			result = false;
		} finally {
			writeLock.unlock();
		}
		return result;
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean result = true;
		writeLock.lock();
		try {
			result = super.removeAll(c);
		} catch (Exception e) {
			result = false;
		} finally {
			writeLock.unlock();
		}
		return result;
	}

	@Override
	public void clear() {
		writeLock.lock();
		try {
			super.clear();
		} catch (Exception e) {
			
		} finally {
			writeLock.unlock();
		}
	}

}
