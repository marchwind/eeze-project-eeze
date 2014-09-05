package com.terascope.amano.incheon.batch;


public class BusBuilder {
	private static MainThreadBus mBus;
	static {
		mBus = new MainThreadBus();
	}
	
	public static MainThreadBus getInstance() {
		return mBus;
	}
}
