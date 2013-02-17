/*******************************************************************************
 * Copyright (c) 2012 Committers of lunifera.org - Lunifera.org.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cristiano Gavião - initial API and implementation
 *******************************************************************************/
package org.lunifera.runtime.common.dispose.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.knowhowlab.osgi.testing.assertions.BundleAssert.assertBundleAvailable;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.knowhowlab.osgi.testing.assertions.BundleAssert;
import org.knowhowlab.osgi.testing.assertions.ServiceAssert;
import org.lunifera.runtime.common.dispose.AbstractDisposable;
import org.lunifera.runtime.common.dispose.IDisposable;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

public class AbstractDisposableTest {

	private static BundleContext bc;

	@BeforeClass
	public static void init() {
		bc = FrameworkUtil.getBundle(AbstractDisposableTest.class)
				.getBundleContext();
		ServiceAssert.setDefaultBundleContext(bc);
		BundleAssert.setDefaultBundleContext(bc);
	}

	public void ensureNeedBundlesWasInstalled() {
		assertBundleAvailable("Runtime-Common bundle is not available",
				"org.lunifera.runtime.common");
	}

	@Test
	public void test_isDisposed() {
		Disposable d = new Disposable();
		assertFalse(d.isDisposed());

		d.dispose();
		assertTrue(d.isDisposed());
	}

	@Test
	public void test_isDisposing() {
		final Disposable d = new Disposable(500);
		assertFalse(d.isDisposing());

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				d.dispose();
			}
		});
		thread.start();

		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
		}
		assertTrue(d.isDisposing());

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		assertTrue(d.isDisposed());
	}

	@Test
	public void test_internalDispose() {
		Disposable d = new Disposable();
		assertEquals(0, d.counter);

		d.dispose();
		assertEquals(1, d.counter);

		// only dipose once!
		d.dispose();
		assertEquals(1, d.counter);
	}

	@Test
	public void test_checkDisposed() {
		Disposable d = new Disposable();
		d.dispose();

		// unchecked calls
		d.isDisposed();
		d.isDisposing();
		d.dispose();

		// checked calls
		Listener listener = new Listener();
		try {
			d.addDisposeListener(listener);
			fail("Exception expected");
		} catch (Exception e) {
			// expected
		}

		try {
			d.removeDisposeListener(listener);
			fail("Exception expected");
		} catch (Exception e) {
			// expected
		}
	}

	@Test
	public void test_DisposeListener() {
		Disposable d = new Disposable();
		Listener listener1 = new Listener();
		Listener listener2 = new Listener();
		Listener listener3 = new Listener();

		d.addDisposeListener(listener1);
		d.addDisposeListener(listener2);
		d.addDisposeListener(listener3);
		assertEquals(3, d.getDisposeListeners().size());
		
		// remove listener3 again
		d.removeDisposeListener(listener3);
		assertEquals(2, d.getDisposeListeners().size());
		
		d.dispose();
		assertEquals(1, listener1.counter);
		assertEquals(1, listener2.counter);
		assertEquals(0, listener3.counter);
		assertEquals(0, d.getDisposeListeners().size());

		// dispose twice
		d.dispose();
		assertEquals(1, listener1.counter);
		assertEquals(1, listener2.counter);
		assertEquals(0, listener3.counter);
	}

	@Test
	public void test_DisposeListener_disposing() {
		final Disposable d = new Disposable(500);
		Listener listener = new Listener();
		d.addDisposeListener(listener);

		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
			}
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					d.dispose();
				}
			});
			thread.start();
		}

		assertEquals(1, listener.counter);
	}

	private static class Disposable extends AbstractDisposable {

		private int counter;
		private int delay;

		private Disposable() {

		}

		private Disposable(int delay) {
			this.delay = delay;
		}

		@Override
		protected void internalDispose() {
			counter++;
			if (delay > 0) {
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
				}
			}
		}

		@Override
		public List<IDisposable.Listener> getDisposeListeners() {
			return super.getDisposeListeners();
		}

	}

	private static class Listener implements IDisposable.Listener {

		private int counter;

		@Override
		public void notifyDisposed(IDisposable notifier) {
			counter++;
		}

	}
}
