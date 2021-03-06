/**
 * Copyright (c) 2012 Committers of lunifera.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Florian Pirchner - initial API and implementation
 */
package org.lunifera.runtime.common.dispose;

import java.util.ArrayList;
import java.util.List;

/**
 * Base implementation for {@link IDisposable}. Subclasses should override
 * {@link #internalDispose()} to dispose their content.
 */
public abstract class AbstractDisposable implements IDisposable {

	private volatile boolean disposed;
	private volatile boolean disposing;
	private List<IDisposable.Listener> disposeListeners;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDisposed() {
		return disposed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDisposing() {
		return disposing;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		// check disposed first
		if (!canDispose()) {
			return;
		}

		synchronized (this) {
			// check disposed again for threads that have been waiting in lock
			if (!canDispose()) {
				return;
			}

			try {
				disposing = true;

				if (!isDisposed()) {
					internalDispose();
				}

				// first call the dispose listener and the set disposed=true
				notifyDisposeListeners();

				// clear the dispose listener
				//
				if (disposeListeners != null) {
					disposeListeners.clear();
					disposeListeners = null;
				}

			} finally {
				disposed = true;
				disposing = false;
			}
		}
	}

	/**
	 * Returns true if not disposing and not disposed.
	 * 
	 * @return
	 */
	protected boolean canDispose() {
		return !isDisposing() && !isDisposed();
	}

	/**
	 * Should be overridden by subclasses to dispose their content.
	 */
	protected abstract void internalDispose();

	/**
	 * Checks whether the element is disposed. Throws a DisposeException is the
	 * element is disposed.
	 * 
	 * @throws DisposeException
	 */
	protected void checkDisposed() {
		IDisposable.DisposableUtil.checkDisposed(this);
	}

	@Override
	public void addDisposeListener(Listener listener) {
		checkDisposed();

		if (listener == null) {
			return;
		}

		if (disposeListeners == null) {
			disposeListeners = new ArrayList<IDisposable.Listener>();
		}

		if (!disposeListeners.contains(listener)) {
			disposeListeners.add(listener);
		}
	}

	@Override
	public void removeDisposeListener(Listener listener) {
		checkDisposed();

		if (listener == null || disposeListeners == null) {
			return;
		}

		disposeListeners.remove(listener);
	}

	/**
	 * Notifies all listeners about the disposal of that elemenyElement.
	 */
	protected void notifyDisposeListeners() {
		if (disposeListeners == null) {
			return;
		}

		for (IDisposable.Listener listener : disposeListeners
				.toArray(new IDisposable.Listener[disposeListeners.size()])) {
			listener.notifyDisposed(this);
		}
	}

	/**
	 * Returns a mutable list of dispose listener.
	 * 
	 * @return the disposeListeners
	 */
	protected List<IDisposable.Listener> getDisposeListeners() {
		return disposeListeners != null ? new ArrayList<IDisposable.Listener>(
				disposeListeners) : new ArrayList<IDisposable.Listener>();
	}

}
